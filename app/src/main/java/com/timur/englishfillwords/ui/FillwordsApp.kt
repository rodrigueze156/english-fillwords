package com.timur.englishfillwords.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.timur.englishfillwords.data.Daily
import com.timur.englishfillwords.data.Difficulty
import com.timur.englishfillwords.data.Levels
import com.timur.englishfillwords.data.ProgressStore
import com.timur.englishfillwords.ui.theme.ThemeStore

private sealed interface Screen {
    data object Home : Screen
    data class Levels(val difficulty: Difficulty) : Screen
    data class Game(val difficulty: Difficulty, val levelIndex: Int) : Screen
    data object Halloween : Screen
    data object Daily : Screen
    data object Achievements : Screen
    data object Statistics : Screen
}

@Composable
fun FillwordsApp(themeStore: ThemeStore) {
    val context = LocalContext.current
    val progress = remember { ProgressStore(context.applicationContext) }
    var screen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (val current = screen) {
        Screen.Home -> HomeScreen(
            progress = progress,
            themeStore = themeStore,
            onOpenLevels = { screen = Screen.Levels(it) },
            onOpenHalloween = { screen = Screen.Halloween },
            onOpenDaily = { screen = Screen.Daily },
            onOpenAchievements = { screen = Screen.Achievements },
            onOpenStatistics = { screen = Screen.Statistics }
        )

        is Screen.Levels -> LevelsScreen(
            difficulty = current.difficulty,
            progress = progress,
            onBack = { screen = Screen.Home },
            onStartLevel = {
                screen = Screen.Game(current.difficulty, it)
            }
        )

        is Screen.Game -> {
            val hasNext = current.levelIndex + 1 < Levels.PER_DIFFICULTY
            GameScreen(
                spec = Levels.spec(current.difficulty, current.levelIndex),
                gameKey = "${current.difficulty.name}_${current.levelIndex}",
                subtitle = "${current.difficulty.label} · Уровень ${current.levelIndex + 1} из ${Levels.PER_DIFFICULTY}",
                tag = current.difficulty.label,
                hasNext = hasNext,
                progress = progress,
                onBack = { screen = Screen.Levels(current.difficulty) },
                onLevels = { screen = Screen.Levels(current.difficulty) },
                onNext = if (hasNext) {
                    { screen = Screen.Game(current.difficulty, current.levelIndex + 1) }
                } else {
                    null
                }
            )
        }

        Screen.Halloween -> GameScreen(
            spec = Levels.halloweenSpec(),
            gameKey = ProgressStore.HALLOWEEN_KEY,
            subtitle = "Хеллоуин · особый уровень",
            tag = "Хеллоуин",
            hasNext = false,
            progress = progress,
            onBack = { screen = Screen.Home },
            onLevels = { screen = Screen.Home },
            onNext = null
        )

        Screen.Daily -> {
            val dateKey = Daily.todayKey()
            GameScreen(
                spec = Daily.spec(dateKey),
                gameKey = Daily.progressKey(dateKey),
                subtitle = "Ежедневный уровень",
                tag = "Ежедневный",
                hasNext = false,
                progress = progress,
                onBack = { screen = Screen.Home },
                onLevels = { screen = Screen.Home },
                onNext = null,
                seed = Daily.seed(dateKey),
                onCompleted = { progress.recordDailyCompletion(dateKey) }
            )
        }

        Screen.Achievements -> AchievementsScreen(
            progress = progress,
            onBack = { screen = Screen.Home }
        )

        Screen.Statistics -> StatisticsScreen(
            progress = progress,
            onBack = { screen = Screen.Home }
        )
    }
}
