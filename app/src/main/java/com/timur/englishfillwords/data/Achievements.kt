package com.timur.englishfillwords.data

/** Достижение с текущим прогрессом. */
data class Achievement(
    val id: String,
    val emoji: String,
    val title: String,
    val description: String,
    val current: Int,
    val target: Int
) {
    val unlocked: Boolean get() = current >= target
}

object Achievements {

    fun list(progress: ProgressStore): List<Achievement> {
        val totalLevels = Levels.PER_DIFFICULTY * Difficulty.entries.size
        val totalStars = totalLevels * 3
        return listOf(
            Achievement(
                "first_step", "🎯", "Первый шаг",
                "Пройди первый уровень", progress.totalCompleted(), 1
            ),
            Achievement(
                "ten_levels", "🧩", "Разминка",
                "Пройди 10 уровней", progress.totalCompleted(), 10
            ),
            Achievement(
                "half_way", "🏅", "Половина пути",
                "Пройди половину уровней", progress.totalCompleted(), totalLevels / 2
            ),
            Achievement(
                "all_levels", "👑", "Все уровни",
                "Пройди все уровни", progress.totalCompleted(), totalLevels
            ),
            Achievement(
                "thirty_stars", "⭐", "Звёздный старт",
                "Собери 30 звёзд", progress.totalStars(), 30
            ),
            Achievement(
                "hundred_stars", "🌟", "Созвездие",
                "Собери 100 звёзд", progress.totalStars(), 100
            ),
            Achievement(
                "all_stars", "💫", "Максимум",
                "Собери все звёзды", progress.totalStars(), totalStars
            ),
            Achievement(
                "daily_three", "🔥", "Серия из трёх",
                "Проходи ежедневный уровень 3 дня подряд", progress.currentDailyStreak(), 3
            ),
            Achievement(
                "daily_week", "📅", "Неделя без пропусков",
                "Серия из 7 ежедневных уровней", progress.dailyStreak, 7
            ),
            Achievement(
                "daily_ten", "🗓", "Каждый день",
                "Пройди 10 ежедневных уровней", progress.dailyCompletedCount, 10
            ),
            Achievement(
                "halloween", "🎃", "Хеллоуин",
                "Пройди хеллоуинский уровень", progress.halloweenStars(), 1
            )
        )
    }
}
