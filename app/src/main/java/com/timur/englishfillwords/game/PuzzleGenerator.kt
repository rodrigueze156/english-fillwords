package com.timur.englishfillwords.game

import com.timur.englishfillwords.data.Direction
import com.timur.englishfillwords.data.Theme
import com.timur.englishfillwords.data.Word
import kotlin.math.max
import kotlin.random.Random

data class Cell(val row: Int, val col: Int)

data class PlacedWord(
    val word: String,
    val translation: String,
    val cells: List<Cell>,
    val colorIndex: Int
)

data class Puzzle(
    val theme: Theme,
    val size: Int,
    val letters: List<List<Char>>,
    val words: List<PlacedWord>
)

/**
 * Раскладывает слова темы по сетке в случайных направлениях.
 * Слова могут пересекаться, если буквы в пересечении совпадают.
 */
object PuzzleGenerator {

    private const val EMPTY = '\u0000'
    private const val FILLER =
        "EEEEEEEEEEEETTTTTTTTTAAAAAAAAOOOOOOOIIIIIIINNNNNNNSSSSSS" +
            "HHHHHHRRRRRDDDDLLLLCCCUUUMMMWWWFFFGGGYYPPBBVVKKJXQZ"

    fun generate(
        theme: Theme,
        size: Int,
        wordCount: Int,
        directions: List<Direction>,
        random: Random = Random.Default
    ): Puzzle {
        val eligible = theme.words.filter { it.en.length in 3..size }
        check(eligible.isNotEmpty()) { "No words fit a $size x $size grid" }

        repeat(300) {
            val puzzle = attempt(theme, size, wordCount, directions, eligible, random)
            if (puzzle != null && puzzle.words.size == wordCount) return puzzle
        }

        // Крайне маловероятный запасной вариант: берём лучшее, что получилось.
        var best: Puzzle? = null
        repeat(60) {
            val puzzle = attempt(theme, size, wordCount, directions, eligible, random) ?: return@repeat
            if (best == null || puzzle.words.size > best!!.words.size) best = puzzle
        }
        return best ?: error("Could not generate puzzle for ${theme.id}")
    }

    private fun attempt(
        theme: Theme,
        size: Int,
        wordCount: Int,
        directions: List<Direction>,
        eligible: List<Word>,
        random: Random
    ): Puzzle? {
        val grid = Array(size) { CharArray(size) { EMPTY } }
        val placed = mutableListOf<Pair<Word, List<Cell>>>()

        // Берём немного больше слов, чем нужно: часть может не поместиться.
        val pool = eligible.shuffled(random)
            .take(minOf(eligible.size, wordCount + 4))
            .sortedByDescending { it.en.length }

        for (word in pool) {
            if (placed.size >= wordCount) break
            if (placed.any { it.first.en == word.en }) continue
            val cells = findPlacement(grid, word.en, size, directions, random) ?: continue
            word.en.forEachIndexed { i, ch -> grid[cells[i].row][cells[i].col] = ch }
            placed += word to cells
        }

        if (placed.isEmpty()) return null

        val filled = grid.map { row ->
            row.map { if (it == EMPTY) FILLER[random.nextInt(FILLER.length)] else it }
        }

        val shuffled = placed.shuffled(random)
        return Puzzle(
            theme = theme,
            size = size,
            letters = filled,
            words = shuffled.mapIndexed { index, (word, cells) ->
                PlacedWord(word.en, word.ru, cells, index)
            }
        )
    }

    private fun findPlacement(
        grid: Array<CharArray>,
        word: String,
        size: Int,
        directions: List<Direction>,
        random: Random
    ): List<Cell>? {
        val length = word.length
        var best: List<Cell>? = null
        var bestCrossings = -1

        repeat(160) { attempt ->
            val direction = directions[random.nextInt(directions.size)]
            val rowMin = max(0, -direction.dr * (length - 1))
            val rowMax = minOf(size - 1, size - 1 - direction.dr * (length - 1))
            val colMin = max(0, -direction.dc * (length - 1))
            val colMax = minOf(size - 1, size - 1 - direction.dc * (length - 1))
            if (rowMin > rowMax || colMin > colMax) return@repeat

            val startRow = rowMin + random.nextInt(rowMax - rowMin + 1)
            val startCol = colMin + random.nextInt(colMax - colMin + 1)
            val cells = (0 until length).map { i ->
                Cell(startRow + direction.dr * i, startCol + direction.dc * i)
            }

            var crossings = 0
            var valid = true
            for (i in cells.indices) {
                val existing = grid[cells[i].row][cells[i].col]
                if (existing != EMPTY) {
                    if (existing != word[i]) {
                        valid = false
                        break
                    }
                    crossings++
                }
            }
            // Полное наложение на уже стоящее слово недопустимо.
            if (!valid || crossings == length) return@repeat

            if (crossings > bestCrossings) {
                bestCrossings = crossings
                best = cells
            }
            // Хорошее пересечение найдено — дальше искать не нужно.
            if (crossings >= 2 || (crossings > 0 && attempt >= 50)) return@repeat
        }
        return best
    }
}
