package com.timur.englishfillwords.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Локальное хранилище прогресса: звёзды по каждому уровню каждой сложности.
 * Уровень открыт, если предыдущий пройден хотя бы на одну звезду.
 */
class ProgressStore(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    /** Меняется после каждой записи, чтобы UI пересчитал прогресс. */
    var revision by mutableIntStateOf(0)
        private set

    /** Общий запас подсказок: копится между уровнями. */
    var hints by mutableIntStateOf(prefs.getInt(KEY_HINTS, STARTING_HINTS))
        private set

    /** Сколько подсказок начислено за прошлые уровни и ещё не показано игроку. */
    var pendingHintGrant by mutableIntStateOf(prefs.getInt(KEY_PENDING_GRANT, 0))
        private set

    /** Серия ежедневных уровней подряд. */
    var dailyStreak by mutableIntStateOf(prefs.getInt(KEY_DAILY_STREAK, 0))
        private set

    /** Последний день, за который пройден ежедневный уровень. */
    var dailyLastKey by mutableStateOf(prefs.getString(KEY_DAILY_LAST, null))
        private set

    /** Сколько ежедневных уровней пройдено всего. */
    var dailyCompletedCount by mutableIntStateOf(prefs.getInt(KEY_DAILY_TOTAL, 0))
        private set

    /** Режим администратора: открывает все уровни, даже закрытые. */
    var admin by mutableStateOf(prefs.getBoolean(KEY_ADMIN, false))
        private set

    /** Включает или выключает режим администратора и сохраняет выбор. */
    fun setAdminMode(value: Boolean) {
        if (admin == value) return
        admin = value
        prefs.edit().putBoolean(KEY_ADMIN, value).apply()
        revision++
    }

    init {
        migrateHints()
    }

    private fun migrateHints() {
        if (prefs.getBoolean(KEY_HINTS_MIGRATED, false)) return
        val completed = totalCompleted() + if (halloweenStars() > 0) 1 else 0
        val award = completed * HINTS_PER_LEVEL
        val editor = prefs.edit().putBoolean(KEY_HINTS_MIGRATED, true)
        if (award > 0) {
            hints += award
            pendingHintGrant = award
            editor.putInt(KEY_HINTS, hints).putInt(KEY_PENDING_GRANT, award)
        }
        editor.apply()
    }

    fun acknowledgeHintGrant() {
        pendingHintGrant = 0
        prefs.edit().remove(KEY_PENDING_GRANT).apply()
    }

    fun addHints(count: Int) {
        if (count <= 0) return
        hints += count
        prefs.edit().putInt(KEY_HINTS, hints).apply()
    }

    /** Тратит одну подсказку. Возвращает false, если запас пуст. */
    fun spendHint(): Boolean {
        if (hints <= 0) return false
        hints -= 1
        prefs.edit().putInt(KEY_HINTS, hints).apply()
        return true
    }

    fun stars(difficulty: Difficulty, levelIndex: Int): Int =
        starsOf(campaignKey(difficulty, levelIndex))

    /** Звёзды за отдельный хеллоуинский уровень. */
    fun halloweenStars(): Int = starsOf(HALLOWEEN_KEY)

    /** Звёзды за ежедневный уровень указанного дня. */
    fun dailyStars(dateKey: String): Int = starsOf(Daily.progressKey(dateKey))

    /** Отмечает прохождение ежедневного уровня и обновляет серию. */
    fun recordDailyCompletion(dateKey: String) {
        if (dailyLastKey == dateKey) return
        dailyStreak = Daily.nextStreak(dailyLastKey, dateKey, dailyStreak)
        dailyLastKey = dateKey
        dailyCompletedCount += 1
        prefs.edit()
            .putInt(KEY_DAILY_STREAK, dailyStreak)
            .putString(KEY_DAILY_LAST, dateKey)
            .putInt(KEY_DAILY_TOTAL, dailyCompletedCount)
            .apply()
    }

    /** Уровни, пройденные на три звезды. */
    fun perfectCount(): Int = Difficulty.entries.sumOf { difficulty ->
        (0 until Levels.PER_DIFFICULTY).count { stars(difficulty, it) == 3 }
    }

    /**
     * Актуальная серия: если после последнего пройденного дня пропущен
     * хотя бы один день, серия уже прервана и показывается как 0.
     */
    fun currentDailyStreak(): Int {
        val last = dailyLastKey ?: return 0
        val today = Daily.todayKey()
        return if (Daily.isStreakActive(last, today)) dailyStreak else 0
    }

    fun starsOf(key: String): Int = prefs.getInt(prefKey(key), 0)

    fun isUnlocked(difficulty: Difficulty, levelIndex: Int): Boolean =
        levelIndex == 0 || stars(difficulty, levelIndex - 1) > 0

    fun completedCount(difficulty: Difficulty): Int =
        (0 until Levels.PER_DIFFICULTY).count { stars(difficulty, it) > 0 }

    fun starsFor(difficulty: Difficulty): Int =
        (0 until Levels.PER_DIFFICULTY).sumOf { stars(difficulty, it) }

    fun totalCompleted(): Int = Difficulty.entries.sumOf { completedCount(it) }

    fun totalStars(): Int = Difficulty.entries.sumOf { starsFor(it) }

    fun recordWin(difficulty: Difficulty, levelIndex: Int, stars: Int) =
        recordStars(campaignKey(difficulty, levelIndex), stars)

    fun recordHalloweenWin(stars: Int) = recordStars(HALLOWEEN_KEY, stars)

    fun recordStars(key: String, stars: Int) {
        if (stars <= 0) return
        if (stars > starsOf(key)) {
            prefs.edit().putInt(prefKey(key), stars).apply()
        }
        revision++
    }

    fun resetAll() {
        prefs.edit().clear().apply()
        hints = STARTING_HINTS
        pendingHintGrant = 0
        dailyStreak = 0
        dailyLastKey = null
        dailyCompletedCount = 0
        admin = false
        revision++
    }

    private fun campaignKey(difficulty: Difficulty, levelIndex: Int) =
        "${difficulty.name}_$levelIndex"

    private fun prefKey(key: String) = "stars_$key"

    companion object {
        const val HALLOWEEN_KEY = "HALLOWEEN"

        /** Стартовый запас подсказок. */
        const val STARTING_HINTS = 3

        /** Столько подсказок даётся за первое прохождение уровня. */
        const val HINTS_PER_LEVEL = 3

        private const val KEY_HINTS = "hints"
        private const val KEY_HINTS_MIGRATED = "hints_migrated"
        private const val KEY_PENDING_GRANT = "hints_pending_grant"
        private const val KEY_DAILY_STREAK = "daily_streak"
        private const val KEY_DAILY_LAST = "daily_last"
        private const val KEY_DAILY_TOTAL = "daily_total"
        private const val KEY_ADMIN = "admin_mode"
        private const val PREFS = "english_fillwords_progress"
    }
}
