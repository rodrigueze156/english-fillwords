package com.timur.englishfillwords.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DailyTest {

    @Test
    fun `level is deterministic for the same date`() {
        val first = Daily.spec("2026-10-30")
        val second = Daily.spec("2026-10-30")

        assertEquals(first.theme.id, second.theme.id)
        assertEquals(first.size, second.size)
        assertEquals(first.wordCount, second.wordCount)
        assertEquals(Daily.seed("2026-10-30"), Daily.seed("2026-10-30"))
    }

    @Test
    fun `different dates give different seeds`() {
        assertNotEquals(Daily.seed("2026-10-30"), Daily.seed("2026-10-31"))
    }

    @Test
    fun `previous key goes to the previous day`() {
        assertEquals("2026-10-29", Daily.previousKey("2026-10-30"))
        assertEquals("2025-12-31", Daily.previousKey("2026-01-01"))
        assertEquals("2028-02-29", Daily.previousKey("2028-03-01"))
    }

    @Test
    fun `daily spec fits the campaign constraints`() {
        val spec = Daily.spec(Daily.todayKey())
        assertTrue(spec.size in 7..12)
        assertTrue(spec.wordCount <= spec.size)
        assertTrue(spec.wordCount <= spec.theme.words.size)
    }

    @Test
    fun `streak grows only on consecutive days`() {
        assertEquals(1, Daily.nextStreak(null, "2026-10-30", 0))
        assertEquals(1, Daily.nextStreak("2026-10-28", "2026-10-30", 5))
        assertEquals(3, Daily.nextStreak("2026-10-29", "2026-10-30", 2))
        assertEquals(2, Daily.nextStreak("2026-10-30", "2026-10-30", 2))
    }

    @Test
    fun `streak is active for today and yesterday only`() {
        assertTrue(Daily.isStreakActive("2026-10-30", "2026-10-30"))
        assertTrue(Daily.isStreakActive("2026-10-29", "2026-10-30"))
        assertTrue(!Daily.isStreakActive("2026-10-28", "2026-10-30"))
        assertTrue(!Daily.isStreakActive(null, "2026-10-30"))
    }
}
