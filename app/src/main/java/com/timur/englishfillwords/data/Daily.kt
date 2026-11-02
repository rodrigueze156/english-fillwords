package com.timur.englishfillwords.data

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Ежедневный уровень: одна и та же головоломка в течение дня.
 * Ключ и сид зависят только от даты, поэтому уровень воспроизводим.
 */
object Daily {

    private const val PATTERN = "yyyy-MM-dd"

    fun todayKey(): String = format(Calendar.getInstance())

    fun previousKey(dateKey: String): String {
        val calendar = parse(dateKey) ?: return dateKey
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return format(calendar)
    }

    fun progressKey(dateKey: String): String = "DAILY_$dateKey"

    /** Человекочитаемая дата, например «30 октября». */
    fun displayDate(dateKey: String): String {
        val calendar = parse(dateKey) ?: return dateKey
        return SimpleDateFormat("d MMMM", Locale("ru", "RU")).format(calendar.time)
    }

    fun seed(dateKey: String): Long = dateKey.hashCode().toLong()

    /** Новая серия после прохождения дня [dateKey]. */
    fun nextStreak(lastKey: String?, dateKey: String, current: Int): Int = when (lastKey) {
        dateKey -> current
        previousKey(dateKey) -> current + 1
        else -> 1
    }

    /** Серия ещё актуальна, если последний день — сегодня или вчера. */
    fun isStreakActive(lastKey: String?, todayKey: String): Boolean =
        lastKey != null && (lastKey == todayKey || lastKey == previousKey(todayKey))

    fun spec(dateKey: String): LevelSpec {
        val index = Math.floorMod(dateKey.hashCode(), Themes.all.size)
        return LevelSpec(
            difficulty = Difficulty.MEDIUM,
            index = 0,
            theme = Themes.all[index],
            size = 11,
            wordCount = 8,
            directions = Directions.all,
            parSeconds = 180
        )
    }

    private fun format(calendar: Calendar): String =
        SimpleDateFormat(PATTERN, Locale.US).format(calendar.time)

    private fun parse(dateKey: String): Calendar? = try {
        val date: Date = SimpleDateFormat(PATTERN, Locale.US).parse(dateKey) ?: return null
        Calendar.getInstance().apply { time = date }
    } catch (_: Exception) {
        null
    }
}
