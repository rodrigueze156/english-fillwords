package com.timur.englishfillwords.update

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.timur.englishfillwords.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

data class UpdateInfo(
    val versionCode: Int,
    val versionName: String,
    val apkUrl: String,
    val notes: String
)

sealed interface UpdateState {
    data object Idle : UpdateState
    data object Checking : UpdateState
    data object UpToDate : UpdateState
    data class Available(val info: UpdateInfo) : UpdateState
    data class Downloading(val progress: Float?) : UpdateState
    data class Ready(val info: UpdateInfo, val file: File) : UpdateState
    data class Failed(val message: String) : UpdateState
}

/**
 * Проверяет JSON-манифест обновлений, скачивает APK и запускает системный установщик.
 *
 * Формат манифеста (лежит на вашем HTTPS-хостинге):
 * {
 *   "versionCode": 2,
 *   "versionName": "1.1",
 *   "apkUrl": "https://example.com/english-fillwords-1.1.apk",
 *   "notes": "Что нового"
 * }
 */
class UpdateViewModel(application: Application) : AndroidViewModel(application) {

    var state by mutableStateOf<UpdateState>(UpdateState.Idle)
        private set

    fun checkForUpdates() {
        val current = state
        if (current is UpdateState.Checking || current is UpdateState.Downloading) return

        viewModelScope.launch {
            state = UpdateState.Checking
            state = try {
                val info = withContext(Dispatchers.IO) { fetchInfo() }
                if (info == null) UpdateState.UpToDate else UpdateState.Available(info)
            } catch (error: Exception) {
                UpdateState.Failed(error.message ?: "Не удалось проверить обновления")
            }
        }
    }

    fun download(info: UpdateInfo) {
        if (state is UpdateState.Downloading) return

        viewModelScope.launch {
            state = UpdateState.Downloading(null)
            try {
                val file = withContext(Dispatchers.IO) { downloadApk(info) }
                state = UpdateState.Ready(info, file)
            } catch (error: Exception) {
                state = UpdateState.Failed(error.message ?: "Не удалось скачать обновление")
            }
        }
    }

    /** @return true, если установщик запущен; false — если нужно разрешение. */
    fun install(): Boolean {
        val ready = state as? UpdateState.Ready ?: return false
        val context = getApplication<Application>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            !context.packageManager.canRequestPackageInstalls()
        ) {
            val settings = Intent(
                Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                Uri.parse("package:${context.packageName}")
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(settings)
            return false
        }

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            ready.file
        )
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        return true
    }

    private fun fetchInfo(): UpdateInfo? {
        if (UpdateConfig.MANIFEST_URL.contains("example.com")) {
            error("Сервер обновлений не настроен. Укажите адрес при сборке: -PupdateUrl=https://…")
        }
        val connection = (URL(UpdateConfig.MANIFEST_URL).openConnection() as HttpURLConnection).apply {
            connectTimeout = 15_000
            readTimeout = 20_000
            instanceFollowRedirects = true
            setRequestProperty("User-Agent", "EnglishFillwords-Updater")
        }
        try {
            val code = connection.responseCode
            if (code !in 200..299) error("Сервер вернул HTTP $code")
            val body = connection.inputStream.bufferedReader().use { it.readText() }
            val json = JSONObject(body)
            val versionCode = json.optInt("versionCode", 0)
            val apkUrl = json.optString("apkUrl").trim()
            if (apkUrl.isEmpty()) error("В манифесте не указан apkUrl")
            if (versionCode <= BuildConfig.VERSION_CODE) return null
            return UpdateInfo(
                versionCode = versionCode,
                versionName = json.optString("versionName", versionCode.toString()),
                apkUrl = apkUrl,
                notes = json.optString("notes", "")
            )
        } finally {
            connection.disconnect()
        }
    }

    private fun downloadApk(info: UpdateInfo): File {
        val folder = File(getApplication<Application>().cacheDir, "updates").apply { mkdirs() }
        val file = File(folder, "english-fillwords-${info.versionCode}.apk")
        if (file.exists()) file.delete()

        val connection = (URL(info.apkUrl).openConnection() as HttpURLConnection).apply {
            connectTimeout = 15_000
            readTimeout = 60_000
            instanceFollowRedirects = true
            setRequestProperty("User-Agent", "EnglishFillwords-Updater")
        }
        try {
            val code = connection.responseCode
            if (code !in 200..299) error("Сервер вернул HTTP $code")
            val total = connection.contentLengthLong
            var downloaded = 0L
            connection.inputStream.use { input ->
                file.outputStream().use { output ->
                    val buffer = ByteArray(64 * 1024)
                    while (true) {
                        val read = input.read(buffer)
                        if (read < 0) break
                        output.write(buffer, 0, read)
                        downloaded += read
                        state = UpdateState.Downloading(
                            if (total > 0) (downloaded.toFloat() / total).coerceIn(0f, 1f) else null
                        )
                    }
                }
            }
            if (file.length() == 0L) error("Скачанный файл пуст")
            return file
        } finally {
            connection.disconnect()
        }
    }
}

object UpdateConfig {
    /** Можно переопределить при сборке: -PupdateUrl=https://... */
    val MANIFEST_URL: String = BuildConfig.UPDATE_URL
}
