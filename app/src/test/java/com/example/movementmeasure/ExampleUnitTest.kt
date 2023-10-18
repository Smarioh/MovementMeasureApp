package com.example.movementmeasure

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val mainActivity = MainActivity()

    @Test
    fun testCalculateMagnitude() {
        val magnitude = mainActivity.calculateMagnitude(1.0f, 2.0f, 2.0f)
        assertEquals(3.0f, magnitude, 0.01f) // with delta for float comparison
    }

    @Test
    fun testIsMagnitudeAboveThreshold() {
        mainActivity.sensitivityThreshold = 10
        assertTrue(mainActivity.isMagnitudeAboveThreshold(11.0f))
        assertFalse(mainActivity.isMagnitudeAboveThreshold(9.0f))
    }
}
