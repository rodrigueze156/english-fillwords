# English Fillwords

Android-игра в слова: находите английские слова в сетке из букв, учите переводы
и проходите тематические уровни. Интерфейс на русском, игра работает офлайн.

## Возможности

- **43 темы / 129 уровней** — по одному уровню в лёгкой, средней и сложной
  сложности для каждой темы.
- **Ежедневный уровень** и серия дней.
- **Достижения и статистика** прогресса.
- **Подсказки** и звёзды за прохождение.
- **Офлайн-игра** — сеть нужна только для проверки обновлений.
- Автоматическое обновление через GitHub Releases.

## Установка

Скачайте последний APK:

```
https://github.com/rodrigueze156/english-fillwords/releases/latest/download/EnglishFillwords.apk
```

## Канал обновлений

Приложение читает манифест:

```
https://github.com/rodrigueze156/english-fillwords/releases/latest/download/update.json
```

Файлы релиза:

- `update.json` — номер версии и ссылка на APK;
- `EnglishFillwords.apk` — установочный файл.

## Сборка

Требования: JDK 21, Android SDK (compileSdk 35), Gradle 8.9.

```powershell
$env:JAVA_HOME = "C:\Program Files\Android\openjdk\jdk-21.0.8"
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"

# debug + unit-тесты
.\gradlew.bat :app:testDebugUnitTest :app:assembleDebug

# release
.\gradlew.bat :app:assembleRelease `
  -PupdateUrl=https://github.com/rodrigueze156/english-fillwords/releases/latest/download/update.json
```

Release-подпись настраивается через `keystore.properties` и `keystore/`
(в репозиторий не попадают).

UI-тесты (нужен эмулятор или устройство):

```powershell
.\gradlew.bat :app:connectedDebugAndroidTest
```

## Добавление темы

Параметры уровней вычисляются автоматически, поэтому новая тема добавляется
только в `Themes.kt`:

1. объявить `val` с 16 словами и переводами;
2. дописать тему **в конец** `Themes.all`;
3. прогнать `:app:testDebugUnitTest`.

Подробности — в `AGENTS.md`.

## Релиз

```powershell
.\release.cmd -Version 1.7 -Notes "Что нового" -DryRun   # план
.\release.cmd -Version 1.7 -Notes "Что нового"           # сборка без публикации
.\release.cmd -Version 1.7 -Notes "Что нового" -Publish  # публикация
```

Скрипт поднимает `versionCode`, обновляет `update.json`, проверяет версию внутри
APK и публикует GitHub Release.

Также доступен ручной запуск GitHub Actions workflow **Release**
(`.github/workflows/release.yml`). Для него нужны secrets с ключом подписи —
список в `AGENTS.md`.

## Структура

```
app/src/main/java/com/timur/englishfillwords/
  data/      темы, уровни, прогресс, ежедневный уровень
  game/      генератор сетки и состояние игры
  ui/        Compose-экраны
  update/    проверка обновлений
```
