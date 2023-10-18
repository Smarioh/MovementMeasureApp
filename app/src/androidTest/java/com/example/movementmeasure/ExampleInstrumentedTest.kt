package com.example.movementmeasure

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

import androidx.test.espresso.matcher.ViewMatchers.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun verifySeekBarUpdatesSensitivityText() {
        // Start the MainActivity.
        Espresso.onView(withId(R.id.sensitivitySeekBar)).perform(ViewActions.setProgress(5))
        Espresso.onView(withId(R.id.sensitivityTextView))
            .check(ViewAssertions.matches(withText("Sensitivity: 5")))
    }

    @Test
    fun verifyInitialSensitivityText() {
        // Start the MainActivity.
        Espresso.onView(withId(R.id.sensitivityTextView))
            .check(ViewAssertions.matches(withText("Sensitivity: 10")))
    }
}