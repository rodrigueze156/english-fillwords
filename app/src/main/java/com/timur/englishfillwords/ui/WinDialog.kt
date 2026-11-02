package com.timur.englishfillwords.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.timur.englishfillwords.game.Game
import com.timur.englishfillwords.ui.theme.Gold
import kotlinx.coroutines.delay

@Composable
fun WinDialog(
    game: Game,
    hasNext: Boolean,
    onNext: () -> Unit,
    onRestart: () -> Unit,
    onLevels: () -> Unit
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = game.spec.theme.emoji, fontSize = 44.sp)
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Тема пройдена!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = FontFamily.Serif
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "«${game.spec.theme.nameRu}» · ${game.tag}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(18.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    repeat(3) { index ->
                        AnimatedStar(filled = index < game.stars, delayMillis = index * 140L)
                    }
                }

                Spacer(Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatTile("Время", formatTime(game.seconds), Modifier.weight(1f))
                    StatTile("Слова", "${game.foundCount}", Modifier.weight(1f))
                    StatTile("Подсказки", "${game.hintsUsed}", Modifier.weight(1f))
                }

                if (hasNext) {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Открыт следующий уровень",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(Modifier.height(18.dp))

                Button(
                    onClick = if (hasNext) onNext else onLevels,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = if (hasNext) "Следующий уровень" else "К уровням",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onLevels,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Все уровни")
                }

                TextButton(onClick = onRestart) {
                    Text(
                        text = "Пройти заново",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimatedStar(filled: Boolean, delayMillis: Long) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(delayMillis)
        visible = true
    }
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.45f, stiffness = 500f),
        label = "winStar"
    )
    Text(
        text = "★",
        modifier = Modifier.scale(scale),
        fontSize = 38.sp,
        color = if (filled) Gold else MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
    )
}
