package com.example.quizz.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ChoiceUi(
    id : Int,
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButton(
    onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(id),
            withText(text),
            isAssignableFrom(AppCompatButton::class.java),
            isDisplayed()
        )
    )
){
    fun assertAvalibleToChooseState() {
        interaction.check(matches(ButtonColorMatcher("#F18080")))
            .check(matches(isEnabled()))
            .check(matches(isClickable()))
    }

    fun assertNotAvalibleToChooseState() {
        interaction.check(matches(isNotEnabled()))
    }

    fun assertCorrectState() {
        interaction.check(matches(ButtonColorMatcher("#3AC13E")))
        .check(matches(isEnabled()))
        .check(matches(isNotClickable()))
    }

    fun assertIncorrectState() {
        interaction.check(matches(ButtonColorMatcher("#9C0E0E")))
            .check(matches(isEnabled()))
            .check(matches(isNotClickable()))
    }

}
