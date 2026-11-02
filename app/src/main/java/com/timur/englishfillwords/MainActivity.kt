package com.timur.englishfillwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.timur.englishfillwords.ui.FillwordsApp
import com.timur.englishfillwords.ui.theme.EnglishFillwordsTheme
import com.timur.englishfillwords.ui.theme.ThemeMode
import com.timur.englishfillwords.ui.theme.ThemeStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val themeStore = ThemeStore(applicationContext)
        setContent {
            EnglishFillwordsTheme(mode = themeStore.mode) {
                val view = LocalView.current
                SideEffect {
                    WindowCompat.getInsetsController(window, view).apply {
                        val light = themeStore.mode == ThemeMode.LIGHT
                        isAppearanceLightStatusBars = light
                        isAppearanceLightNavigationBars = light
                    }
                }
                FillwordsApp(themeStore = themeStore)
            }
        }
    }
}
