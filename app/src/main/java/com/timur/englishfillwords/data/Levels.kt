package com.timur.englishfillwords.data

/** Направление, в котором слово укладывается в сетку. */
data class Direction(val dr: Int, val dc: Int)

object Directions {
    val east = Direction(0, 1)
    val south = Direction(1, 0)
    val southEast = Direction(1, 1)
    val northEast = Direction(-1, 1)
    val west = Direction(0, -1)
    val north = Direction(-1, 0)
    val northWest = Direction(-1, -1)
    val southWest = Direction(1, -1)

    /** Только горизонталь и вертикаль — самый простой вариант. */
    val basic = listOf(east, south)

    /** Четыре диагональных направления без «обратных». */
    val four = listOf(east, south, southEast, northEast)

    /** Все восемь направлений. */
    val all = listOf(east, south, southEast, northEast, west, north, northWest, southWest)
}

enum class Difficulty(
    val label: String,
    val tagline: String,
    val emoji: String
) {
    EASY("Лёгкий", "Знакомство с игрой", "🌱"),
    MEDIUM("Средний", "Слова во все стороны", "🌿"),
    HARD("Сложный", "Большие сетки и таймер", "🌳")
}

data class LevelSpec(
    val difficulty: Difficulty,
    val index: Int,
    val theme: Theme,
    val size: Int,
    val wordCount: Int,
    val directions: List<Direction>,
    val parSeconds: Int
)

object Levels {
    /** Количество уровней в каждой сложности = количество тем. */
    val PER_DIFFICULTY = Themes.all.size

    /**
     * Параметры уровня вычисляются из сложности и порядкового номера темы.
     * Поэтому новая тема в [Themes.all] автоматически получает готовый уровень —
     * править больше ничего не нужно.
     *
     * Формулы насыщаются: после определённого номера сетка и норма времени
     * перестают расти, так что добавление тем в конец не меняет старые уровни.
     */
    private data class Tuning(val size: Int, val words: Int, val par: Int)

    private fun tuning(difficulty: Difficulty, index: Int): Tuning = when (difficulty) {
        // 7→11 клеток, 5→10 слов; сетка растёт каждые 4 темы.
        Difficulty.EASY -> {
            val size = 7 + minOf(4, index / 4)
            Tuning(
                size = size,
                words = minOf(size - 1, 5 + index / 4),
                par = minOf(470, 90 + index * 10)
            )
        }
        // 9→12 клеток, 7→12 слов; сетка растёт каждые 5 тем.
        Difficulty.MEDIUM -> {
            val size = 9 + minOf(3, index / 5)
            Tuning(
                size = size,
                words = minOf(size, 7 + index / 3),
                par = minOf(540, 150 + index * 10)
            )
        }
        // 10→12 клеток, 8→12 слов; сетка растёт каждые 6 тем.
        Difficulty.HARD -> {
            val size = 10 + minOf(2, index / 6)
            Tuning(
                size = size,
                words = minOf(size, 8 + index / 2),
                par = minOf(590, 180 + index * 10)
            )
        }
    }

    fun spec(difficulty: Difficulty, index: Int): LevelSpec {
        val safeIndex = index.coerceIn(0, PER_DIFFICULTY - 1)
        val tuning = tuning(difficulty, safeIndex)
        val dirs = when (difficulty) {
            Difficulty.EASY -> if (safeIndex < 4) Directions.basic else Directions.four
            Difficulty.MEDIUM -> Directions.four
            Difficulty.HARD -> Directions.all
        }
        return LevelSpec(
            difficulty = difficulty,
            index = safeIndex,
            theme = Themes.all[safeIndex],
            size = tuning.size,
            wordCount = tuning.words,
            directions = dirs,
            parSeconds = tuning.par
        )
    }

    /** Отдельный хеллоуинский уровень: не входит в кампании и не влияет на их прогресс. */
    fun halloweenSpec(): LevelSpec = LevelSpec(
        difficulty = Difficulty.MEDIUM,
        index = 0,
        theme = Themes.halloween,
        size = 11,
        wordCount = 8,
        directions = Directions.all,
        parSeconds = 180
    )
}
