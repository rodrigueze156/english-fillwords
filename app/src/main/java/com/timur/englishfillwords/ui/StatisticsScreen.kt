package com.timur.englishfillwords.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timur.englishfillwords.data.Achievements
import com.timur.englishfillwords.data.Daily
import com.timur.englishfillwords.data.Difficulty
import com.timur.englishfillwords.data.Levels
import com.timur.englishfillwords.data.ProgressStore
import com.timur.englishfillwords.ui.theme.Gold
import com.timur.englishfillwords.ui.theme.Terracotta

private val statsMediumAccent = Color(0xFFB07A2A)

private fun statsAccent(difficulty: Difficulty): Color = when (difficulty) {
    Difficulty.EASY -> Color(0xFF4C8C5A)
    Difficulty.MEDIUM -> statsMediumAccent
    Difficulty.HARD -> Terracotta
}

@Composable
fun StatisticsScreen(
    progress: ProgressStore,
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)
    val revision = progress.revision

    val totalLevels = Levels.PER_DIFFICULTY * Difficulty.entries.size
    val completed = progress.totalCompleted()
    val stars = progress.totalStars()
    val perfect = progress.perfectCount()
    val achievements = Achievements.list(progress)
    val unlockedAchievements = achievements.count { it.unlocked }

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundIconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Статистика",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Твой путь в цифрах",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatTile(
                    label = "звёзд",
                    value = "$stars",
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    label = "уровней",
                    value = "$completed/$totalLevels",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatTile(
                    label = "на три звезды",
                    value = "$perfect",
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    label = "подсказок",
                    value = "${progress.hints}",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatTile(
                    label = "достижений",
                    value = "$unlockedAchievements/${achievements.size}",
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    label = "хеллоуин",
                    value = if (progress.halloweenStars() > 0) "★".repeat(progress.halloweenStars()) else "—",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(24.dp))
            SectionLabel("ПО СЛОЖНОСТИ")
            Spacer(Modifier.height(10.dp))

            Difficulty.entries.forEach { difficulty ->
                DifficultyStatsRow(
                    difficulty = difficulty,
                    completed = progress.completedCount(difficulty),
                    stars = progress.starsFor(difficulty)
                )
                Spacer(Modifier.height(10.dp))
            }

            Spacer(Modifier.height(14.dp))
            SectionLabel("ЕЖЕДНЕВНЫЙ УРОВЕНЬ")
            Spacer(Modifier.height(10.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    StatsLine("Серия дней подряд", "🔥 ${progress.currentDailyStreak()}")
                    StatsLine("Пройдено всего", "${progress.dailyCompletedCount}")
                    StatsLine(
                        "Сегодня",
                        if (progress.dailyStars(Daily.todayKey()) > 0) "Пройден" else "Ещё не пройден"
                    )
                    StatsLine(
                        "Последний день",
                        progress.dailyLastKey?.let(Daily::displayDate) ?: "—",
                        last = true
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Данные хранятся только на этом устройстве.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DifficultyStatsRow(
    difficulty: Difficulty,
    completed: Int,
    stars: Int
) {
    val accent = statsAccent(difficulty)
    val totalStars = Levels.PER_DIFFICULTY * 3
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.28f))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(accent.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = difficulty.emoji, fontSize = 19.sp)
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = difficulty.label,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "$completed/${Levels.PER_DIFFICULTY} · ★ $stars/$totalStars",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(Modifier.height(7.dp))
                LinearProgressIndicator(
                    progress = { completed.toFloat() / Levels.PER_DIFFICULTY },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(CircleShape),
                    color = accent,
                    trackColor = accent.copy(alpha = 0.15f),
                    drawStopIndicator = {}
                )
            }
        }
    }
}

@Composable
private fun StatsLine(label: String, value: String, last: Boolean = false) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        if (!last) DividerLine(modifier = Modifier.height(1.dp))
    }
}
