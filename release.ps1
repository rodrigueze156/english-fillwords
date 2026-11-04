#Requires -Version 5.1
<#
.SYNOPSIS
    Собирает и (по желанию) публикует новый релиз English Fillwords.

.DESCRIPTION
    Поднимает versionCode, обновляет versionName и update.json, прогоняет тесты,
    собирает release-APK, проверяет версию внутри APK и копирует его в
    EnglishFillwords.apk. Без ключа -Publish ничего не заливает на GitHub.

.EXAMPLE
    .\release.ps1 -Version 1.7 -Notes "Новая тема «Космос»" -Publish

.EXAMPLE
    .\release.ps1 -Version 1.7 -SkipTests
#>
[CmdletBinding()]
param(
    # Новый versionName, например 1.7. Если не указан — увеличивается последняя цифра.
    [string]$Version,

    # Заметки к релизу. Используются только при -Publish.
    [string]$Notes = "",

    # Залить релиз на GitHub. Без этого флага только сборка и проверка.
    [switch]$Publish,

    # Пропустить unit-тесты (не рекомендуется).
    [switch]$SkipTests,

    # Только показать план, ничего не менять и не собирать.
    [switch]$DryRun
)

$ErrorActionPreference = "Stop"
$repoDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $repoDir

$repoSlug = "rodrigueze156/english-fillwords"
$updateUrl = "https://github.com/$repoSlug/releases/latest/download/update.json"
$apkUrl = "https://github.com/$repoSlug/releases/latest/download/EnglishFillwords.apk"
$gradleFile = Join-Path $repoDir "app\build.gradle.kts"
$updateFile = Join-Path $repoDir "update.json"
$releaseApk = Join-Path $repoDir "app\build\outputs\apk\release\app-release.apk"
$targetApk = Join-Path $repoDir "EnglishFillwords.apk"

function Get-Gh {
    $fromPath = Get-Command gh -ErrorAction SilentlyContinue
    if ($fromPath) { return $fromPath.Source }
    $fallback = "C:\Program Files\GitHub CLI\gh.exe"
    if (Test-Path $fallback) { return $fallback }
    throw "GitHub CLI (gh) не найден. Установи gh или добавь его в PATH."
}

function Write-Utf8NoBom([string]$Path, [string]$Text) {
    $encoding = New-Object System.Text.UTF8Encoding($false)
    [System.IO.File]::WriteAllText($Path, $Text, $encoding)
}

if (-not (Test-Path $gradleFile)) {
    throw "Не найден $gradleFile. Запускай скрипт из папки проекта."
}

# --- текущая версия ------------------------------------------------------

$gradleOriginal = [System.IO.File]::ReadAllText($gradleFile, [System.Text.Encoding]::UTF8)
$codeMatch = [regex]::Match($gradleOriginal, 'versionCode\s*=\s*(\d+)')
$nameMatch = [regex]::Match($gradleOriginal, 'versionName\s*=\s*"([^"]+)"')
if (-not $codeMatch.Success -or -not $nameMatch.Success) {
    throw "Не удалось прочитать versionCode/versionName из app/build.gradle.kts"
}

$currentCode = [int]$codeMatch.Groups[1].Value
$currentName = $nameMatch.Groups[1].Value
$newCode = $currentCode + 1

if (-not $Version) {
    $parts = $currentName.Split('.')
    $parts[$parts.Length - 1] = ([int]$parts[$parts.Length - 1] + 1).ToString()
    $Version = $parts -join '.'
}
if ($Version -notmatch '^\d+\.\d+(\.\d+)?$') {
    throw "Некорректная версия «$Version». Ожидается формат 1.7 или 1.7.1."
}

if (-not $Notes.Trim()) {
    $Notes = "Обновление $Version."
}

Write-Host "Текущая версия: $currentName ($currentCode)" -ForegroundColor Cyan
Write-Host "Новая версия:   $Version ($newCode)" -ForegroundColor Cyan

# --- защита от повторной публикации -------------------------------------

$publishedCode = $null
try {
    $latest = Invoke-RestMethod -Uri $updateUrl -TimeoutSec 20
    $publishedCode = [int]$latest.versionCode
    Write-Host "Опубликованная версия на GitHub: $($latest.versionName) ($publishedCode)" -ForegroundColor DarkGray
} catch {
    Write-Warning "Не удалось проверить последнюю версию на GitHub: $($_.Exception.Message)"
}

if ($null -ne $publishedCode -and $newCode -le $publishedCode) {
    throw "versionCode $newCode не больше опубликованного $publishedCode. Обнови проект или укажи другую версию."
}

if ($DryRun) {
    Write-Host "`nСухой прогон: файлы не изменены." -ForegroundColor Yellow
    Write-Host "Будет: versionName $Version, versionCode $newCode, update.json, сборка."
    if ($Publish) {
        Write-Host "Публикация: да, v$Version."
    } else {
        Write-Host "Публикация: нет."
    }
    return
}

$gh = $null
if ($Publish) {
    $gh = Get-Gh
    & $gh api user --jq ".login" 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) {
        throw "GitHub CLI не авторизован. Выполни: gh auth login"
    }
    $existingTags = (& $gh release list --repo $repoSlug --limit 100 --json tagName | ConvertFrom-Json).tagName
    if ($existingTags -contains "v$Version") {
        throw "Релиз v$Version уже существует на GitHub."
    }
}

# --- правка версии и манифеста ------------------------------------------

$updateOriginal = if (Test-Path $updateFile) {
    [System.IO.File]::ReadAllText($updateFile, [System.Text.Encoding]::UTF8)
} else {
    $null
}

$gradleUpdated = [regex]::Replace($gradleOriginal, 'versionCode\s*=\s*\d+', "versionCode = $newCode")
$gradleUpdated = [regex]::Replace($gradleUpdated, 'versionName\s*=\s*"[^"]+"', "versionName = `"$Version`"")

$manifest = [ordered]@{
    versionCode = $newCode
    versionName = $Version
    apkUrl = $apkUrl
    notes = $Notes
}
$manifestJson = ($manifest | ConvertTo-Json) + "`n"

$restoreOnFailure = $true
try {
    Write-Utf8NoBom $gradleFile $gradleUpdated
    Write-Utf8NoBom $updateFile $manifestJson

    # --- сборка ---------------------------------------------------------

    $env:JAVA_HOME = "C:\Program Files\Android\openjdk\jdk-21.0.8"
    $env:ANDROID_HOME = Join-Path $env:LOCALAPPDATA "Android\Sdk"
    $env:ANDROID_SDK_ROOT = $env:ANDROID_HOME

    $gradle = Join-Path $env:LOCALAPPDATA "Temp\opencode\gradle\gradle-8.9\bin\gradle.bat"
    if (-not (Test-Path $gradle)) {
        $gradle = Join-Path $repoDir "gradlew.bat"
    }
    if (-not (Test-Path $gradle)) {
        throw "Gradle не найден ни в Temp\opencode, ни в папке проекта."
    }

    $tasks = @()
    if (-not $SkipTests) { $tasks += ":app:testDebugUnitTest" }
    $tasks += ":app:assembleRelease"

    Write-Host "`nСборка: $($tasks -join ', ')" -ForegroundColor Cyan
    & $gradle @tasks "-PupdateUrl=$updateUrl" --no-daemon --console=plain
    if ($LASTEXITCODE -ne 0) { throw "Gradle завершился с кодом $LASTEXITCODE" }

    if (-not (Test-Path $releaseApk)) {
        throw "APK не найден: $releaseApk"
    }

    # --- проверка версии внутри APK ------------------------------------

    $aapt = Get-ChildItem (Join-Path $env:ANDROID_HOME "build-tools\*\aapt.exe") -ErrorAction SilentlyContinue |
        Sort-Object { [version]$_.Directory.Name } -Descending |
        Select-Object -First 1
    if ($aapt) {
        $badging = (& $aapt.FullName dump badging $releaseApk 2>$null | Select-Object -First 1)
        if ($badging -match "versionCode='(\d+)'\s+versionName='([^']+)'") {
            $apkCode = [int]$Matches[1]
            $apkName = $Matches[2]
            Write-Host "В APK: $apkName ($apkCode)" -ForegroundColor DarkGray
            if ($apkCode -ne $newCode -or $apkName -ne $Version) {
                throw "Версия в APK ($apkName/$apkCode) не совпадает с ожидаемой ($Version/$newCode)."
            }
        } else {
            Write-Warning "Не удалось разобрать вывод aapt, проверка версии APK пропущена."
        }
    } else {
        Write-Warning "aapt не найден, проверка версии APK пропущена."
    }

    Copy-Item $releaseApk $targetApk -Force
    $sizeMb = [math]::Round((Get-Item $targetApk).Length / 1MB, 2)
    Write-Host "APK готов: EnglishFillwords.apk ($sizeMb МБ)" -ForegroundColor Green

    Write-Host "`nupdate.json:" -ForegroundColor Cyan
    Write-Host $manifestJson

    # --- публикация -----------------------------------------------------

    if (-not $Publish) {
        Write-Host "Публикация не запрошена. Файлы версии обновлены, APK собран." -ForegroundColor Yellow
        Write-Host "Чтобы залить: .\release.ps1 -Version $Version -Notes `"...`" -Publish" -ForegroundColor Yellow
        return
    }

    # Коммитим только версию и манифест, чтобы история релизов была в git.
    & git add -- $gradleFile $updateFile 2>$null
    & git commit -m "Release $Version" 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) {
        throw "git commit завершился с кодом $LASTEXITCODE"
    }
    $restoreOnFailure = $false

    $hasRemote = $false
    & git remote get-url origin 2>$null | Out-Null
    if ($LASTEXITCODE -eq 0) { $hasRemote = $true }

    if ($hasRemote) {
        Write-Host "Отправка коммита и тега v$Version в origin..." -ForegroundColor Cyan
        & git push --quiet origin HEAD:main 2>$null
        if ($LASTEXITCODE -ne 0) { throw "git push завершился с кодом $LASTEXITCODE" }
        & git tag -f "v$Version" 2>$null | Out-Null
        & git push --quiet --force origin "v$Version" 2>$null
        if ($LASTEXITCODE -ne 0) { throw "git push тега завершился с кодом $LASTEXITCODE" }
    } else {
        Write-Warning "Remote origin не настроен: тег будет создан только на GitHub."
    }

    $notesFile = Join-Path $env:TEMP "fillwords-release-$Version.txt"
    Write-Utf8NoBom $notesFile ($Notes.Trim() + "`n")

    Write-Host "`nПубликация v$Version..." -ForegroundColor Cyan
    $releaseArgs = @(
        "release", "create", "v$Version",
        "--repo", $repoSlug,
        "--title", "English Fillwords $Version",
        "--notes-file", $notesFile
    )
    if ($hasRemote) { $releaseArgs += "--verify-tag" }

    & $gh @releaseArgs "update.json" "EnglishFillwords.apk"
    if ($LASTEXITCODE -ne 0) { throw "gh release create завершился с кодом $LASTEXITCODE" }

    # --- проверка публикации -------------------------------------------

    $ok = $false
    for ($i = 1; $i -le 10; $i++) {
        try {
            $latest = Invoke-RestMethod -Uri $updateUrl -TimeoutSec 20
            if ([int]$latest.versionCode -eq $newCode -and $latest.versionName -eq $Version) {
                $ok = $true
                break
            }
        } catch {
            # CDN может отдавать старый файл несколько секунд.
        }
        Start-Sleep -Seconds 3
    }
    if (-not $ok) {
        throw "GitHub всё ещё отдаёт старый update.json. Проверь релиз вручную."
    }

    $head = Invoke-WebRequest -Uri $apkUrl -Method Head -TimeoutSec 30
    if ($head.StatusCode -ne 200) {
        throw "APK по ссылке обновления недоступен (HTTP $($head.StatusCode))."
    }

    Write-Host "`nГотово: https://github.com/$repoSlug/releases/tag/v$Version" -ForegroundColor Green
} catch {
    if ($restoreOnFailure) {
        Write-Utf8NoBom $gradleFile $gradleOriginal
        if ($null -ne $updateOriginal) {
            Write-Utf8NoBom $updateFile $updateOriginal
        }
        Write-Warning "Сборка не удалась, версия и update.json возвращены к $currentName ($currentCode)."
    }
    throw
}
