package com.example.quizz.stats

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.quizz.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class StatsUi(
    incorrects: Int, corrects: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {
    private val interaction: ViewInteraction =
        onView(
            allOf(
                withId(R.id.statusTextView),
                containerIdMatcher,
                containerClassTypeMatcher,
                isAssignableFrom(TextView::class.java),
                withText("Game Over\n\nCorrects: $corrects\nIncorrects: $incorrects"),
            )
        )

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertDosNotExist() {
        interaction.check(doesNotExist())
    }
}
