# English Fillwords — инструкции для агента

Android-игра «English Fillwords»: офлайн-поиск английских слов, русский интерфейс.
Пакет: `com.timur.englishfillwords`. Обновления раздаются через GitHub Releases.

## Главные правила

- **Не публиковать релизы и не менять версию без явного разрешения пользователя.**
  Слова «собери», «проверь» не означают «залей на GitHub».
- Не делать лишних действий: правки строго по задаче.
- Все тексты в интерфейсе — на русском. Бренд-строка «Word Puzzle by rodrigueze» остаётся.
- Новые темы добавляются **только в конец** `Themes.all`, иначе сдвинутся номера
  уровней и старый прогресс переедет на другие темы.
- Параметры уровней (сетка, число слов, время) вычисляются автоматически в
  `Levels.tuning()`. Для нового уровня правится **только `Themes.kt`**.
- Перед сборкой релиза обязательно прогнать unit-тесты.
- Секреты (`keystore.properties`, `keystore/`, `local.properties`) в git не попадают.

## Структура

| Путь | Назначение |
|---|---|
| `app/src/main/java/com/timur/englishfillwords/data/Themes.kt` | Темы, слова, переводы, эмодзи; `Themes.all` задаёт порядок уровней |
| `.../data/Levels.kt` | Сложности, направления, автоматический расчёт параметров уровня |
| `.../data/Daily.kt` | Ежедневный уровень, серия дней |
| `.../data/ProgressStore.kt` | Прогресс, звёзды, достижения, статистика (SharedPreferences) |
| `.../game/PuzzleGenerator.kt` | Генерация сетки и размещение слов |
| `.../game/GameViewModel.kt` | Состояние игры, подсказки, рестарт |
| `.../ui/` | Compose-экраны (`HomeScreen`, `GameScreen`, `WordGrid`) |
| `update.json` | Манифест обновления, его читает приложение |
| `app/build.gradle.kts` | `versionCode`, `versionName`, подпись, `UPDATE_URL` |

## Сборка (Windows, PowerShell)

Окружение:

```powershell
$env:JAVA_HOME = "C:\Program Files\Android\openjdk\jdk-21.0.8"
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
$env:ANDROID_SDK_ROOT = $env:ANDROID_HOME
```

Gradle 8.9 (проект рассчитан именно на неё, JDK 21):

```powershell
& "$env:LOCALAPPDATA\Temp\opencode\gradle\gradle-8.9\bin\gradle.bat" <tasks> --no-daemon --console=plain
```

Либо wrapper из папки проекта: `.\gradlew.bat <tasks>`.

Типовые команды:

```powershell
# тесты + debug
.\gradlew.bat :app:testDebugUnitTest :app:assembleDebug

# релиз (URL обновления обязателен)
.\gradlew.bat :app:assembleRelease -PupdateUrl=https://github.com/rodrigueze156/english-fillwords/releases/latest/download/update.json
```

Результат: `app/build/outputs/apk/release/app-release.apk`.

## Как добавить уровень

1. В `Themes.kt` объявить тему с 16 словами (A–Z, длина 3–10, без повторов)
   и русскими переводами.
2. Дописать её в конец `Themes.all`.
3. Прогнать `:app:testDebugUnitTest` — тест проверяет все темы во всех
   сложностях и точное число слов в сгенерированной сетке.

`Levels.kt` менять не нужно.

## Релиз

Порядок:

1. Поднять версию в `app/build.gradle.kts`: `versionCode + 1`, `versionName`
   как `"X.Y"`. Не переиспользовать уже опубликованный `versionCode`.
2. Обновить `update.json`: `versionCode`, `versionName`, заметки на русском.
   `apkUrl` не меняется.
3. Собрать release с `-PupdateUrl=...` (см. выше).
4. Скопировать APK:
   ```powershell
   Copy-Item "app\build\outputs\apk\release\app-release.apk" "EnglishFillwords.apk" -Force
   ```
5. Опубликовать (имена ассетов менять нельзя):
   ```powershell
   gh release create vX.Y --repo rodrigueze156/english-fillwords `
     --title "English Fillwords X.Y" --notes "..." `
     "update.json" "EnglishFillwords.apk"
   ```
6. Проверить:
   ```powershell
   (Invoke-RestMethod "https://github.com/rodrigueze156/english-fillwords/releases/latest/download/update.json").versionCode
   (Invoke-WebRequest "https://github.com/rodrigueze156/english-fillwords/releases/latest/download/EnglishFillwords.apk" -Method Head).StatusCode
   ```

Ожидается новый `versionCode` и HTTP 200.

## Emulator

- LDPlayer: `F:\LDPlayer\LDPlayer9`, adb: `F:\LDPlayer\LDPlayer9\adb.exe`, serial `emulator-5554`.
- Запуск: `F:\LDPlayer\LDPlayer9\ldconsole.exe launch --index 0`, ждать ~45 секунд.
- Android 9: команды `input motionevent` нет. Для скриншота в середине drag —
  запустить `input swipe ... 3000` в фоне и снять `screencap`.
- Общая папка эмулятора: `C:\Users\Timur\Documents\XuanZhi9` (создаёт LDPlayer).

## Кодировка и платформа

- Русский текст в файлах — UTF-8. При чтении в PowerShell указывать `-Encoding UTF8`.
- Проект живёт на Windows; пути с пробелами брать в кавычки.
- `ffmpeg`/`ffprobe` установлены через winget (Gyan.FFmpeg 9.0.2) и доступны в PATH
  после перезапуска терминала. Использовать для раскадровки видео с багами.
