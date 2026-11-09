package com.timur.englishfillwords.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timur.englishfillwords.data.Difficulty
import com.timur.englishfillwords.data.LevelSpec
import com.timur.englishfillwords.data.Levels
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

data class Game(
    val id: Long,
    val key: String,
    val spec: LevelSpec,
    val subtitle: String,
    val tag: String,
    val puzzle: Puzzle,
    val seed: Long? = null,
    val found: List<PlacedWord> = emptyList(),
    val hintsUsed: Int = 0,
    val hintedWord: PlacedWord? = null,
    val seconds: Int = 0,
    val mistakes: Int = 0,
    val completed: Boolean = false,
    val stars: Int = 0
) {
    val foundCount: Int get() = found.size
    val totalCount: Int get() = puzzle.words.size
    val progress: Float get() = if (totalCount == 0) 0f else foundCount.toFloat() / totalCount
}

class GameViewModel : ViewModel() {

    var game by mutableStateOf<Game?>(null)
        private set

    private var hintJob: Job? = null
    private var nextId = 1L

    fun start(
        spec: LevelSpec,
        key: String,
        subtitle: String,
        tag: String,
        force: Boolean = false,
        seed: Long? = null
    ) {
        val current = game
        if (!force && current != null && current.key == key) return

        hintJob?.cancel()
        val puzzle = PuzzleGenerator.generate(
            theme = spec.theme,
            size = spec.size,
            wordCount = spec.wordCount,
            directions = spec.directions,
            allowCrossings = spec.difficulty == Difficulty.HARD,
            random = Random(seed ?: System.nanoTime())
        )
        game = Game(
            id = nextId++,
            key = key,
            spec = spec,
            subtitle = subtitle,
            tag = tag,
            puzzle = puzzle,
            seed = seed
        )
    }

    fun restart() {
        val current = game ?: return
        start(current.spec, current.key, current.subtitle, current.tag, force = true, seed = current.seed)
    }

    fun tick() {
        val current = game ?: return
        if (current.completed) return
        game = current.copy(seconds = current.seconds + 1)
    }

    fun submitSelection(cells: List<Cell>) {
        val current = game ?: return
        if (current.completed || cells.size < 2) return

        val foundWords = current.found.mapTo(mutableSetOf()) { it.word }
        val match = current.puzzle.words.firstOrNull {
            it.word !in foundWords && matches(it.cells, cells)
        }
        if (match == null) {
            game = current.copy(mistakes = current.mistakes + 1)
            return
        }

        val found = current.found + match
        val completed = found.size == current.puzzle.words.size
        game = current.copy(
            found = found,
            hintedWord = null,
            completed = completed,
            stars = if (completed) calculateStars(current) else 0
        )
    }

    fun hint(onSpend: () -> Boolean) {
        val current = game ?: return
        if (current.completed) return
        // Пока подсветка активна, повторные нажатия не тратят подсказки.
        if (current.hintedWord != null) return

        val foundWords = current.found.mapTo(mutableSetOf()) { it.word }
        val target = current.puzzle.words.firstOrNull { it.word !in foundWords } ?: return
        if (!onSpend()) return

        hintJob?.cancel()
        game = current.copy(hintsUsed = current.hintsUsed + 1, hintedWord = target)
        hintJob = viewModelScope.launch {
            delay(3200)
            val latest = game
            if (latest != null && latest.id == current.id) {
                game = latest.copy(hintedWord = null)
            }
        }
    }

    private fun calculateStars(g: Game): Int {
        var stars = 3
        if (g.hintsUsed > 0) stars--
        if (g.seconds > g.spec.parSeconds) stars--
        return stars.coerceIn(1, 3)
    }

    private fun matches(placed: List<Cell>, selection: List<Cell>): Boolean =
        placed == selection || placed.asReversed() == selection
}
