package com.timur.englishfillwords.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import com.timur.englishfillwords.data.Theme
import com.timur.englishfillwords.data.Word
import com.timur.englishfillwords.game.Cell
import com.timur.englishfillwords.game.Puzzle
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * Регрессия на баг: после кнопки «Обновить» подсветка выбранных букв пропадала.
 *
 * Причина была в том, что состояние выделения пересоздавалось вместе с Puzzle,
 * а pointerInput оставался привязан к старым объектам состояния. Тест меняет
 * Puzzle (как это делает «Обновить») и проверяет подсветку прямо во время жеста.
 */
class WordGridRefreshTest {

    @get:Rule
    val rule = createComposeRule()

    private val theme = Theme(
        id = "test",
        nameRu = "Тест",
        nameEn = "Test",
        emoji = "🧪",
        words = listOf(Word("TEST", "тест"))
    )

    private fun puzzle(shift: Int): Puzzle = Puzzle(
        theme = theme,
        size = 7,
        letters = List(7) { row ->
            List(7) { col ->
                ('A' + ((row * 7 + col + shift) % 26))
            }
        },
        words = emptyList()
    )

    @Test
    fun highlightSurvivesPuzzleReplacement() {
        var puzzleState by mutableStateOf(puzzle(0))
        var lastSelection by mutableStateOf<List<Cell>>(emptyList())

        rule.setContent {
            WordGrid(
                puzzle = puzzleState,
                foundWords = emptyList(),
                hintedWord = null,
                size = 350.dp,
                enabled = true,
                onSelect = { lastSelection = it },
                modifier = Modifier.testTag("grid")
            )
        }

        // Обычная протяжка по первой строке: выделение работает.
        rule.onNodeWithTag("grid").performTouchInput {
            val step = width / 7f
            val y = step * 0.5f
            down(Offset(step * 0.5f, y))
            moveTo(Offset(step * 0.7f, y), delayMillis = 20)
            moveTo(Offset(step * 1.4f, y), delayMillis = 20)
            moveTo(Offset(step * 2.1f, y), delayMillis = 20)
            moveTo(Offset(step * 2.6f, y), delayMillis = 20)
            up()
        }
        rule.waitForIdle()
        assertTrue("Протяжка должна выделить хотя бы две клетки", lastSelection.size >= 2)

        // Имитируем «Обновить»: новый объект Puzzle при том же размере сетки.
        puzzleState = puzzle(1)
        rule.waitForIdle()

        // Начинаем новую протяжку и не отпускаем палец:
        // проверяем именно подсветку, а не результат onSelect.
        rule.onNodeWithTag("grid").performTouchInput {
            val step = width / 7f
            val y = step * 1.5f
            down(Offset(step * 0.5f, y))
            moveTo(Offset(step * 0.7f, y), delayMillis = 20)
            moveTo(Offset(step * 1.4f, y), delayMillis = 20)
            moveTo(Offset(step * 2.1f, y), delayMillis = 20)
            moveTo(Offset(step * 2.6f, y), delayMillis = 20)
        }
        rule.waitForIdle()

        rule.onNodeWithTag("cell_1_1").assert(
            SemanticsMatcher.expectValue(SemanticsProperties.Selected, true)
        )
        rule.onNodeWithTag("cell_1_2").assert(
            SemanticsMatcher.expectValue(SemanticsProperties.Selected, true)
        )
    }
}
