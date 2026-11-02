package com.timur.englishfillwords.game

import com.timur.englishfillwords.data.Difficulty
import com.timur.englishfillwords.data.LevelSpec
import com.timur.englishfillwords.data.Levels
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs
import kotlin.random.Random

class PuzzleGeneratorTest {

    @Test
    fun allLevelsPlaceEveryWordAsAStraightLine() {
        for (difficulty in Difficulty.entries) {
            for (levelIndex in 0 until Levels.PER_DIFFICULTY) {
                val spec = Levels.spec(difficulty, levelIndex)
                repeat(3) { seed ->
                    assertPuzzle(spec, seed, "$difficulty/$levelIndex")
                }
            }
        }
    }

    @Test
    fun halloweenLevelPlacesEveryWordAsAStraightLine() {
        val spec = Levels.halloweenSpec()
        repeat(5) { seed ->
            assertPuzzle(spec, seed, "halloween")
        }
    }

    private fun assertPuzzle(spec: LevelSpec, seed: Int, label: String) {
        val puzzle = PuzzleGenerator.generate(
            theme = spec.theme,
            size = spec.size,
            wordCount = spec.wordCount,
            directions = spec.directions,
            random = Random(seed)
        )

        assertEquals("word count for $label", spec.wordCount, puzzle.words.size)
        assertEquals(spec.size, puzzle.letters.size)
        assertTrue(puzzle.letters.all { it.size == spec.size })

        for (word in puzzle.words) {
            assertEquals(word.word.length, word.cells.size)
            assertEquals(
                word.word[0].toString(),
                puzzle.letters[word.cells[0].row][word.cells[0].col].toString()
            )

            val dr = word.cells[1].row - word.cells[0].row
            val dc = word.cells[1].col - word.cells[0].col
            assertTrue(
                "word ${word.word} must go straight on $label",
                dr == 0 || dc == 0 || abs(dr) == abs(dc)
            )
            val wordCells = mutableSetOf<Cell>()
            for (i in word.cells.indices) {
                val cell = word.cells[i]
                assertTrue(cell.row in 0 until spec.size)
                assertTrue(cell.col in 0 until spec.size)
                assertEquals(
                    "letter at ${cell.row},${cell.col}",
                    word.word[i],
                    puzzle.letters[cell.row][cell.col]
                )
                if (i > 0) {
                    assertEquals(dr, word.cells[i].row - word.cells[i - 1].row)
                    assertEquals(dc, word.cells[i].col - word.cells[i - 1].col)
                }
                assertTrue("no cell repeats inside a word", wordCells.add(cell))
            }
        }
    }
}
