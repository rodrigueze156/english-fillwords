package com.timur.englishfillwords.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timur.englishfillwords.data.Difficulty
import com.timur.englishfillwords.data.Levels
import com.timur.englishfillwords.data.ProgressStore
import com.timur.englishfillwords.data.Themes
import com.timur.englishfillwords.ui.theme.Gold
import com.timur.englishfillwords.ui.theme.Terracotta

private val mediumAccent = Color(0xFFB07A2A)

private fun accentFor(difficulty: Difficulty): Color = when (difficulty) {
    Difficulty.EASY -> Color(0xFF4C8C5A)
    Difficulty.MEDIUM -> mediumAccent
    Difficulty.HARD -> Terracotta
}

@Composable
fun LevelsScreen(
    difficulty: Difficulty,
    progress: ProgressStore,
    onBack: () -> Unit,
    onStartLevel: (Int) -> Unit
) {
    BackHandler(onBack = onBack)
    val revision = progress.revision
    val accent = accentFor(difficulty)

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
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
                        text = "${difficulty.label} уровень",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = if (progress.admin) {
                            "Режим администратора · открыты все уровни"
                        } else {
                            "${progress.completedCount(difficulty)} из ${Levels.PER_DIFFICULTY} тем пройдено"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Gold.copy(alpha = 0.14f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("★", color = Gold, fontSize = 14.sp)
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "${progress.starsFor(difficulty)}/${Levels.PER_DIFFICULTY * 3}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f),
                border = BorderStroke(1.dp, accent.copy(alpha = 0.25f))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(difficulty.emoji, fontSize = 22.sp)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "${difficulty.tagline}. У каждого уровня своя тема и набор английских слов.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(Themes.all) { index, theme ->
                    val unlocked = progress.isUnlocked(difficulty, index)
                    val enabled = progress.admin || unlocked
                    LevelCard(
                        number = index + 1,
                        themeName = theme.nameRu,
                        themeNameEn = theme.nameEn,
                        emoji = theme.emoji,
                        size = Levels.spec(difficulty, index).size,
                        wordCount = Levels.spec(difficulty, index).wordCount,
                        stars = progress.stars(difficulty, index),
                        unlocked = unlocked,
                        enabled = enabled,
                        accent = accent,
                        onClick = { if (enabled) onStartLevel(index) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LevelCard(
    number: Int,
    themeName: String,
    themeNameEn: String,
    emoji: String,
    size: Int,
    wordCount: Int,
    stars: Int,
    unlocked: Boolean,
    enabled: Boolean,
    accent: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.height(168.dp),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            1.dp,
            if (unlocked) MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
            else MaterialTheme.colorScheme.outline.copy(alpha = 0.18f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (unlocked) accent.copy(alpha = 0.14f)
                            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (unlocked) emoji else "🔒",
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = "№$number",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = themeName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = themeNameEn.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                letterSpacing = 0.6.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "$size×$size · $wordCount слов",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(6.dp))

            if (unlocked) {
                StarRow(stars = stars, starSize = 15.sp)
            } else if (enabled) {
                Text(
                    text = "Доступ администратора",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = accent,
                    maxLines = 1
                )
            } else {
                Text(
                    text = "Пройди предыдущий",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }
        }
    }
}
