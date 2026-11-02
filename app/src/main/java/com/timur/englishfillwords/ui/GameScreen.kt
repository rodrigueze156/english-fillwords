package com.timur.englishfillwords.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.timur.englishfillwords.data.LevelSpec
import com.timur.englishfillwords.data.ProgressStore
import com.timur.englishfillwords.game.Game
import com.timur.englishfillwords.game.GameViewModel
import com.timur.englishfillwords.game.PlacedWord
import com.timur.englishfillwords.ui.theme.Gold
import com.timur.englishfillwords.ui.theme.wordColor
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    spec: LevelSpec,
    gameKey: String,
    subtitle: String,
    tag: String,
    hasNext: Boolean,
    progress: ProgressStore,
    onBack: () -> Unit,
    onLevels: () -> Unit,
    onNext: (() -> Unit)?,
    seed: Long? = null,
    onCompleted: (() -> Unit)? = null
) {
    BackHandler(onBack = onBack)

    val viewModel: GameViewModel = viewModel(key = "game-$gameKey")
    LaunchedEffect(gameKey) {
        viewModel.start(spec, gameKey, subtitle, tag, force = true, seed = seed)
    }

    val game = viewModel.game
    if (game == null) {
        AppBackground {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        return
    }

    val haptics = LocalHapticFeedback.current

    LaunchedEffect(game.id) {
        while (true) {
            delay(1000)
            val current = viewModel.game ?: break
            if (current.id != game.id || current.completed) break
            viewModel.tick()
        }
    }

    LaunchedEffect(game.id, game.completed) {
        val current = viewModel.game
        if (current != null && current.id == game.id && current.completed) {
            val firstCompletion = progress.starsOf(current.key) == 0
            progress.recordStars(current.key, current.stars)
            if (firstCompletion) {
                progress.addHints(ProgressStore.HINTS_PER_LEVEL)
                onCompleted?.invoke()
            }
        }
    }

    LaunchedEffect(game.id, game.foundCount) {
        if (game.foundCount > 0) {
            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    AppBackground {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            val gridSize = minOf(maxWidth, maxHeight * 0.47f, 430.dp)

            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(Modifier.height(8.dp))
                GameTopBar(
                    game = game,
                    hints = progress.hints,
                    onBack = onBack,
                    onRestart = viewModel::restart
                )

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Найдено ${game.foundCount} из ${game.totalCount}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Цель ${formatTime(game.spec.parSeconds)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { game.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                        .clip(CircleShape),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    drawStopIndicator = {}
                )

                Spacer(Modifier.height(12.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    WordGrid(
                        puzzle = game.puzzle,
                        foundWords = game.found,
                        hintedWord = game.hintedWord,
                        size = gridSize,
                        enabled = !game.completed,
                        onSelect = viewModel::submitSelection
                    )
                }

                Spacer(Modifier.height(12.dp))

                WordsPanel(game = game, modifier = Modifier.weight(1f))

                Spacer(Modifier.height(10.dp))

                HintButton(
                    game = game,
                    hints = progress.hints,
                    onClick = { viewModel.hint(progress::spendHint) }
                )

                Spacer(Modifier.height(12.dp))
            }
        }
    }

    if (game.completed) {
        WinDialog(
            game = game,
            hasNext = hasNext,
            onNext = { onNext?.invoke() },
            onRestart = viewModel::restart,
            onLevels = onLevels
        )
    }
}

@Composable
private fun GameTopBar(
    game: Game,
    hints: Int,
    onBack: () -> Unit,
    onRestart: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RoundIconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Назад",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = game.spec.theme.nameRu,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = game.subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Gold.copy(alpha = 0.18f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("💡", fontSize = 12.sp)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "$hints",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(Modifier.width(4.dp))
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("⏱", fontSize = 12.sp)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = formatTime(game.seconds),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(Modifier.width(4.dp))
        RoundIconButton(onClick = onRestart, background = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Начать заново",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun WordsPanel(game: Game, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.28f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionLabel("СЛОВА ТЕМЫ")
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${game.foundCount}/${game.totalCount}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(8.dp))
            val scrollState = rememberScrollState()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    game.puzzle.words.forEach { word ->
                        val found = game.found.any { it.word == word.word }
                        val hinted = game.hintedWord?.word == word.word
                        WordChip(word = word, found = found, hinted = hinted)
                    }
                }
                if (scrollState.canScrollForward) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(top = 4.dp),
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, Gold.copy(alpha = 0.45f))
                    ) {
                        Text(
                            text = "↓ ещё",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Gold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WordChip(word: PlacedWord, found: Boolean, hinted: Boolean) {
    val color = wordColor(word.colorIndex)
    Surface(
        shape = RoundedCornerShape(9.dp),
        color = when {
            found -> color.copy(alpha = 0.15f)
            hinted -> Gold.copy(alpha = 0.14f)
            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.42f)
        },
        border = if (hinted) BorderStroke(1.dp, Gold) else null
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (found) {
                Text(
                    text = "✓",
                    color = color,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(3.dp))
            }
            Text(
                text = word.word,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                textDecoration = if (found) TextDecoration.LineThrough else TextDecoration.None,
                color = if (found) color else MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = word.translation,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun HintButton(game: Game, hints: Int, onClick: () -> Unit) {
    val enabled = hints > 0 && !game.completed && game.hintedWord == null
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = if (enabled) Gold.copy(alpha = 0.15f)
        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 13.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("💡", fontSize = 15.sp)
            Spacer(Modifier.width(8.dp))
            Text(
                text = when {
                    hints == 0 -> "Подсказки закончились"
                    game.hintedWord != null -> "Подсказка показана"
                    else -> "Подсказка"
                },
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = if (enabled) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (hints > 0) {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "×$hints",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Gold
                )
            }
        }
    }
}
