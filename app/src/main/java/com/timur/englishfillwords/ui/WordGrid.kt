package com.timur.englishfillwords.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timur.englishfillwords.game.Cell
import com.timur.englishfillwords.game.PlacedWord
import com.timur.englishfillwords.game.Puzzle
import com.timur.englishfillwords.ui.theme.Gold
import com.timur.englishfillwords.ui.theme.wordColor
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

@Composable
fun WordGrid(
    puzzle: Puzzle,
    foundWords: List<PlacedWord>,
    hintedWord: PlacedWord?,
    size: Dp,
    enabled: Boolean,
    onSelect: (List<Cell>) -> Unit,
    modifier: Modifier = Modifier
) {
    val n = puzzle.size
    val gap = if (n >= 11) 2.dp else 3.dp
    val cellSize = (size - gap * (n - 1)) / n
    val stepPx = with(LocalDensity.current) { (cellSize + gap).toPx() }

    var startCell by remember(puzzle) { mutableStateOf<Cell?>(null) }
    var currentCell by remember(puzzle) { mutableStateOf<Cell?>(null) }
    var selection by remember(puzzle) { mutableStateOf<List<Cell>>(emptyList()) }

    val foundMap = remember(foundWords) {
        val map = mutableMapOf<Cell, PlacedWord>()
        foundWords.forEach { word -> word.cells.forEach { map[it] = word } }
        map
    }
    val hintedCells = remember(hintedWord) { hintedWord?.cells?.toSet() ?: emptySet() }
    val selectionSet = selection.toSet()

    Box(modifier = modifier.size(size)) {
        Column(verticalArrangement = Arrangement.spacedBy(gap)) {
            for (row in 0 until n) {
                Row(horizontalArrangement = Arrangement.spacedBy(gap)) {
                    for (col in 0 until n) {
                        val cell = Cell(row, col)
                        GridCell(
                            letter = puzzle.letters[row][col],
                            cellSize = cellSize,
                            foundColor = foundMap[cell]?.let { wordColor(it.colorIndex) },
                            hinted = cell in hintedCells,
                            selected = cell in selectionSet,
                            edge = cell == selection.firstOrNull() || cell == selection.lastOrNull(),
                            modifier = Modifier.testTag("cell_${row}_$col")
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .pointerInput(puzzle, n, stepPx, enabled) {
                    if (!enabled) return@pointerInput
                    detectDragGestures(
                        onDragStart = { offset ->
                            val cell = cellAt(offset, stepPx, n)
                            startCell = cell
                            currentCell = cell
                            selection = listOf(cell)
                        },
                        onDrag = { change, _ ->
                            change.consume()
                            val start = startCell ?: return@detectDragGestures
                            val cell = cellAt(change.position, stepPx, n)
                            if (cell != currentCell) {
                                currentCell = cell
                                selection = straightPath(start, cell)
                            }
                        },
                        onDragEnd = {
                            val result = selection
                            startCell = null
                            currentCell = null
                            selection = emptyList()
                            if (result.size >= 2) onSelect(result)
                        },
                        onDragCancel = {
                            startCell = null
                            currentCell = null
                            selection = emptyList()
                        }
                    )
                }
        )
    }
}

private fun cellAt(offset: Offset, stepPx: Float, n: Int): Cell {
    val col = (offset.x / stepPx).toInt().coerceIn(0, n - 1)
    val row = (offset.y / stepPx).toInt().coerceIn(0, n - 1)
    return Cell(row, col)
}

private fun straightPath(from: Cell, to: Cell): List<Cell> {
    val dr = to.row - from.row
    val dc = to.col - from.col
    if (dr == 0 && dc == 0) return listOf(from)
    if (dr != 0 && dc != 0 && abs(dr) != abs(dc)) return listOf(from)

    val steps = max(abs(dr), abs(dc))
    val stepRow = dr.sign
    val stepCol = dc.sign
    return (0..steps).map { Cell(from.row + stepRow * it, from.col + stepCol * it) }
}

@Composable
private fun GridCell(
    letter: Char,
    cellSize: Dp,
    foundColor: Color?,
    hinted: Boolean,
    selected: Boolean,
    edge: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(if (cellSize.value > 24f) 7.dp else 5.dp)
    val target = when {
        foundColor != null -> foundColor
        hinted -> Gold.copy(alpha = 0.32f)
        selected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.20f)
        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.48f)
    }
    val background by animateColorAsState(
        targetValue = target,
        animationSpec = tween(durationMillis = 180),
        label = "cellBackground"
    )
    val textColor = when {
        foundColor != null -> Color.White
        selected && edge -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
    }
    val border = when {
        selected && edge -> androidx.compose.foundation.BorderStroke(
            2.dp,
            MaterialTheme.colorScheme.primary
        )
        selected -> androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
        hinted -> androidx.compose.foundation.BorderStroke(1.5.dp, Gold)
        else -> null
    }

    Box(
        modifier = modifier
            .size(cellSize)
            .semantics {
                this.selected = selected
                stateDescription = if (selected) "selected" else "unselected"
            }
            .clip(shape)
            .background(background)
            .then(if (border != null) Modifier.border(border, shape) else Modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            fontSize = (cellSize.value * 0.44f).sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}
