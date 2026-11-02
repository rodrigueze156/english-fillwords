package com.timur.englishfillwords.ui.theme

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class ThemeMode(val label: String, val emoji: String) {
    LIGHT("Светлая", "☀️"),
    DARK("Тёмная", "🌙"),
    HALLOWEEN("Хеллоуинская", "🎃")
}

/** Выбранная тема оформления. По умолчанию — хеллоуинская. */
class ThemeStore(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    var mode by mutableStateOf(load())
        private set

    fun select(mode: ThemeMode) {
        this.mode = mode
        prefs.edit().putString(KEY, mode.name).apply()
    }

    private fun load(): ThemeMode {
        val saved = prefs.getString(KEY, null) ?: return ThemeMode.HALLOWEEN
        return ThemeMode.entries.firstOrNull { it.name == saved } ?: ThemeMode.HALLOWEEN
    }

    private companion object {
        const val PREFS = "english_fillwords_settings"
        const val KEY = "theme_mode"
    }
}
