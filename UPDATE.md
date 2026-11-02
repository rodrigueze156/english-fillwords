# Обновление приложения без магазинов

Приложение **English Fillwords** обновляется напрямую с GitHub Releases — без Google Play, RuStore и других магазинов.

## Как это работает

1. Пользователь нажимает **«Обновить приложение»** на главном экране.
2. Приложение скачивает `update.json` с GitHub.
3. Сравнивает `versionCode` с текущим.
4. Если версия новее — скачивает APK с прогрессом и открывает системный установщик.
5. Пользователь подтверждает установку.

## Где лежат обновления

Публичный репозиторий: **https://github.com/rodrigueze156/english-fillwords**

Стабильные ссылки (всегда указывают на последний релиз):

- манифест: `https://github.com/rodrigueze156/english-fillwords/releases/latest/download/update.json`
- APK: `https://github.com/rodrigueze156/english-fillwords/releases/latest/download/EnglishFillwords.apk`

Эти адреса «зашиты» в приложение при сборке и менять их при каждом релизе не нужно.

## Как выпустить обновление

1. Поднимите версию в `app/build.gradle.kts`:

   ```kotlin
   versionCode = 3        // строго больше предыдущего
   versionName = "1.2"
   ```

2. Соберите подписанный APK:

   ```bat
   gradlew :app:assembleRelease -PupdateUrl=https://github.com/rodrigueze156/english-fillwords/releases/latest/download/update.json
   ```

3. Создайте `update.json` для новой версии:

   ```json
   {
     "versionCode": 3,
     "versionName": "1.2",
     "apkUrl": "https://github.com/rodrigueze156/english-fillwords/releases/latest/download/EnglishFillwords.apk",
     "notes": "Что нового в этой версии."
   }
   ```

4. Опубликуйте релиз (файлы должны называться **точно так же**):

   ```bat
   gh release create v1.2 ^
     --repo rodrigueze156/english-fillwords ^
     --title "English Fillwords 1.2" ^
     --notes "Что нового" ^
     "путь\update.json" ^
     "путь\EnglishFillwords.apk"
   ```

После публикации обновление появится у пользователей при следующей проверке.

## Важно

- **Ключ подписи.** Все версии подписаны ключом `keystore/release.jks` (alias `englishfillwords`). Пароль хранится в `keystore.properties`. Эти два файла нужно сохранить в надёжном месте: без них выпустить обновление поверх установленной версии невозможно.
- **versionCode** должен расти с каждым релизом. Одинаковый или меньший номер приложение проигнорирует.
- **Имя APK-ассета** должно оставаться `EnglishFillwords.apk`, иначе ссылка `latest/download/...` перестанет работать.
- **Кэш GitHub.** После публикации `latest/download/update.json` может обновляться с задержкой до нескольких минут.
- **Разрешение на установку.** Пользователь один раз разрешает установку из этого источника. Если разрешения нет, приложение открывает нужный экран настроек.
- **Google Play.** Если приложение когда-нибудь попадёт в Play, самообновление APK там запрещено правилами — понадобится Play In-App Updates.

## Проверка подписи

```bat
apksigner verify --print-certs EnglishFillwords.apk
```

Ожидаемый сертификат:

```
CN=English Fillwords, OU=Mobile, O=English Fillwords, L=Moscow, ST=Moscow, C=RU
SHA-256: 45:BF:32:0F:44:F5:BF:52:1F:22:F0:FF:6A:16:E6:5C:C7:25:DF:A7:D1:F2:A7:AE:6B:1E:A6:1A:9E:C3:2B:0A
```

## Файлы в проекте

- `update/UpdateViewModel.kt` — проверка версии, скачивание, запуск установщика.
- `ui/UpdateDialog.kt` — диалог обновления.
- `res/xml/file_paths.xml` — FileProvider для передачи APK установщику.
- `app/build.gradle.kts` — версия, `BuildConfig.UPDATE_URL`, release-подпись.
- `keystore.properties` + `keystore/release.jks` — ключ подписи (не публикуются, в `.gitignore`).
