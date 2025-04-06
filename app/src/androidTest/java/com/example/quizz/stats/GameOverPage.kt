package com.example.quizz.stats

import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.quizz.R
import com.example.quizz.game.ButtonUi
import org.hamcrest.Matcher

class GameOverPage(incorrects: Int, corrects: Int) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.gameOverContainer))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(FrameLayout::class.java))

    private val statsUi = StatsUi(
        incorrects = incorrects, corrects = corrects,
        containerIdMatcher, classTypeMatcher
    )

    private val newGameUi = ButtonUi(
        R.id.newGameButton,
        R.string.new_game,
        "#EF9963",
        containerIdMatcher, classTypeMatcher
    )

    fun assertInitialState() {
        statsUi.assertVisible()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.assertDosNotExist()
    }

}
