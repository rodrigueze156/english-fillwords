package com.timur.englishfillwords.ui

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timur.englishfillwords.data.Daily
import com.timur.englishfillwords.data.Difficulty
import com.timur.englishfillwords.data.Levels
import com.timur.englishfillwords.data.ProgressStore
import com.timur.englishfillwords.ui.theme.Gold
import com.timur.englishfillwords.ui.theme.Terracotta
import com.timur.englishfillwords.ui.theme.ThemeMode
import com.timur.englishfillwords.ui.theme.ThemeStore

private val mediumAccent = Color(0xFFB07A2A)

private fun accentFor(difficulty: Difficulty): Color = when (difficulty) {
    Difficulty.EASY -> Color(0xFF4C8C5A)
    Difficulty.MEDIUM -> mediumAccent
    Difficulty.HARD -> Terracotta
}

private fun shortDescription(difficulty: Difficulty): String = when (difficulty) {
    Difficulty.EASY -> "7×7 – 11×11 · 5–10 слов"
    Difficulty.MEDIUM -> "9×9 – 12×12 · 7–12 слов"
    Difficulty.HARD -> "10×10 – 12×12 · 8–12 слов"
}

@Composable
fun HomeScreen(
    progress: ProgressStore,
    themeStore: ThemeStore,
    onOpenLevels: (Difficulty) -> Unit,
    onOpenHalloween: () -> Unit,
    onOpenDaily: () -> Unit,
    onOpenAchievements: () -> Unit,
    onOpenStatistics: () -> Unit
) {
    // Подписка на изменения прогресса.
    val revision = progress.revision
    var showHowTo by remember { mutableStateOf(false) }
    var showReset by remember { mutableStateOf(false) }
    var showUpdate by remember { mutableStateOf(false) }
    var showTheme by remember { mutableStateOf(false) }

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(18.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aa",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Word Puzzle by rodrigueze",
                        style = MaterialTheme.typography.labelSmall,
                        letterSpacing = 0.3.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "English Fillwords",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.weight(1f))

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Gold.copy(alpha = 0.18f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("💡", fontSize = 14.sp)
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "${progress.hints}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(Modifier.height(30.dp))

            Text(
                text = "Находи английские слова",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "${Levels.PER_DIFFICULTY * Difficulty.entries.size} тематических уровня и три уровня сложности. " +
                    "Тяни пальцем по буквам и собирай словарный запас.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(22.dp))

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.surface,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    StatItem(
                        value = "${progress.totalStars()}",
                        label = "звёзд",
                        modifier = Modifier.weight(1f)
                    )
                    StatItem(
                        value = "${progress.totalCompleted()}/${Levels.PER_DIFFICULTY * Difficulty.entries.size}",
                        label = "уровней",
                        modifier = Modifier.weight(1f)
                    )
                    StatItem(
                        value = "${Levels.PER_DIFFICULTY}",
                        label = "тем",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(Modifier.height(26.dp))

            SectionLabel("ЕЖЕДНЕВНЫЙ УРОВЕНЬ")
            Spacer(Modifier.height(10.dp))

            DailyCard(
                dateKey = Daily.todayKey(),
                stars = progress.dailyStars(Daily.todayKey()),
                streak = progress.currentDailyStreak(),
                completedCount = progress.dailyCompletedCount,
                onClick = onOpenDaily
            )

            Spacer(Modifier.height(26.dp))

            SectionLabel("ВЫБЕРИ СЛОЖНОСТЬ")
            Spacer(Modifier.height(10.dp))

            Difficulty.entries.forEach { difficulty ->
                DifficultyCard(
                    difficulty = difficulty,
                    completed = progress.completedCount(difficulty),
                    stars = progress.starsFor(difficulty),
                    onClick = { onOpenLevels(difficulty) }
                )
                Spacer(Modifier.height(12.dp))
            }

            HalloweenCard(
                stars = progress.halloweenStars(),
                onClick = onOpenHalloween
            )

            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                NavigationTile(
                    emoji = "🏆",
                    title = "Достижения",
                    subtitle = "Награды за прогресс",
                    modifier = Modifier.weight(1f),
                    onClick = onOpenAchievements
                )
                NavigationTile(
                    emoji = "📊",
                    title = "Статистика",
                    subtitle = "Цифры и рекорды",
                    modifier = Modifier.weight(1f),
                    onClick = onOpenStatistics
                )
            }

            Spacer(Modifier.height(18.dp))

            OutlinedButton(
                onClick = { showTheme = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Тема: ${themeStore.mode.emoji} ${themeStore.mode.label}",
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = { showHowTo = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Как играть", fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = { showUpdate = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Обновить приложение", fontWeight = FontWeight.SemiBold)
            }

            TextButton(
                onClick = { showReset = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Сбросить прогресс",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "${Levels.PER_DIFFICULTY} тем · ${Levels.PER_DIFFICULTY * 3} уровня · 3 сложности · Хеллоуин",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }

    if (showHowTo) {
        HowToPlayDialog(onDismiss = { showHowTo = false })
    }

    if (showTheme) {
        ThemePickerDialog(
            current = themeStore.mode,
            onSelect = themeStore::select,
            onDismiss = { showTheme = false }
        )
    }

    if (progress.pendingHintGrant > 0) {
        AlertDialog(
            onDismissRequest = progress::acknowledgeHintGrant,
            title = { Text("Подсказки зачислены 🎉") },
            text = {
                Text(
                    "За пройденные уровни начислено подсказок: ${progress.pendingHintGrant}. " +
                        "Их можно тратить в любом уровне — сразу несколько или ни одной."
                )
            },
            confirmButton = {
                TextButton(onClick = progress::acknowledgeHintGrant) {
                    Text("Отлично")
                }
            }
        )
    }

    if (showUpdate) {
        UpdateDialog(onDismiss = { showUpdate = false })
    }

    if (showReset) {
        AlertDialog(
            onDismissRequest = { showReset = false },
            title = { Text("Сбросить прогресс?") },
            text = {
                Text("Все звёзды и открытые уровни будут удалены. Это нельзя отменить.")
            },
            confirmButton = {
                TextButton(onClick = {
                    progress.resetAll()
                    showReset = false
                }) {
                    Text("Сбросить", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showReset = false }) { Text("Отмена") }
            }
        )
    }
}

@Composable
private fun StatItem(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DifficultyCard(
    difficulty: Difficulty,
    completed: Int,
    stars: Int,
    onClick: () -> Unit
) {
    val accent = accentFor(difficulty)
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(accent.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = difficulty.emoji, fontSize = 25.sp)
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = difficulty.label,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(8.dp))
                    StarRow(stars = stars)
                }
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${difficulty.tagline} · ${shortDescription(difficulty)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LinearProgressIndicator(
                        progress = { completed.toFloat() / Levels.PER_DIFFICULTY },
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(CircleShape),
                        color = accent,
                        trackColor = accent.copy(alpha = 0.15f),
                        drawStopIndicator = {}
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "$completed/${Levels.PER_DIFFICULTY}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.width(10.dp))

            Text(
                text = "›",
                fontSize = 26.sp,
                color = accent,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun HalloweenCard(stars: Int, onClick: () -> Unit) {
    val accent = MaterialTheme.colorScheme.primary
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.45f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(accent.copy(alpha = 0.16f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🎃", fontSize = 26.sp)
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Хеллоуинский уровень",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(3.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    StarRow(stars = stars, starSize = 13.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "11×11 · 8 слов",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.width(10.dp))

            Text(
                text = "›",
                fontSize = 26.sp,
                color = accent,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DailyCard(
    dateKey: String,
    stars: Int,
    streak: Int,
    completedCount: Int,
    onClick: () -> Unit
) {
    val accent = MaterialTheme.colorScheme.primary
    val theme = Daily.spec(dateKey).theme
    val done = stars > 0
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, accent.copy(alpha = 0.45f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(accent.copy(alpha = 0.16f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🗓", fontSize = 25.sp)
                }

                Spacer(Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Пазл дня",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = "${theme.emoji} ${theme.nameRu} · ${Daily.displayDate(dateKey)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(Modifier.width(10.dp))

                Surface(
                    shape = RoundedCornerShape(50),
                    color = accent.copy(alpha = 0.14f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🔥", fontSize = 13.sp)
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "$streak",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (done) {
                    StarRow(stars = stars, starSize = 14.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Пройдено сегодня",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Text(
                        text = "11×11 · 8 слов · серия $streak",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    text = if (done) "Ещё раз" else "Играть",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = accent
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "›",
                    fontSize = 22.sp,
                    color = accent,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = "Всего пройдено ежедневных: $completedCount",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun NavigationTile(
    emoji: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(text = emoji, fontSize = 22.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ThemePickerDialog(
    current: ThemeMode,
    onSelect: (ThemeMode) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Тема оформления",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ThemeMode.entries.forEach { mode ->
                    val selected = mode == current
                    Surface(
                        onClick = {
                            onSelect(mode)
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        color = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)
                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        border = if (selected) {
                            BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.55f))
                        } else {
                            null
                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(mode.emoji, fontSize = 20.sp)
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = mode.label,
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.SemiBold
                            )
                            if (selected) {
                                Text(
                                    text = "✓",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Закрыть") }
        }
    )
}

@Composable
private fun HowToPlayDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Как играть", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Rule("1", "Проведи пальцем по буквам в любом направлении: по горизонтали, вертикали или диагонали.")
                Rule("2", "Слова могут быть написаны слева направо и справа налево. Подсказки-переводы — в списке под сеткой.")
                Rule("3", "На уровень даётся 3 подсказки. Чем меньше подсказок и быстрее игра, тем больше звёзд.")
                Rule("4", "Уровни открываются по порядку. У каждой сложности свой прогресс.")
            }
        },
        confirmButton = {
            Button(onClick = onDismiss, shape = RoundedCornerShape(12.dp)) {
                Text("Понятно")
            }
        }
    )
}

@Composable
private fun Rule(number: String, text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(CircleShape)
                .background(Gold.copy(alpha = 0.16f)),
            contentAlignment = Alignment.Center
        ) {
            Text(number, style = MaterialTheme.typography.labelSmall, color = Gold)
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
