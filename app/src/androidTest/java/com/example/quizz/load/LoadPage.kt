package com.example.quizz.load

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.quizz.R
import com.example.quizz.game.ButtonUi
import org.hamcrest.Matcher

class LoadPage {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.loadLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val progressUi = ProgressUi(
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher
    )

    private val errorUi = ErrorUi(
        containerIdMatcher = containerIdMatcher,
        classTypeMatcher = classTypeMatcher,

        )

    private val retryUi = ButtonUi(
        R.id.retruButton,
        R.string.retry,
        "#F18080",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher,
    )

    fun assertProgressState() {
        errorUi.assertNotVisible()
        progressUi.assertVisible()
        retryUi.assertNotVisible()
    }

    fun waitTillError() {
        errorUi.waitTillVisible()
    }

    fun assertErrorState() {
        errorUi.assertVisible()
        progressUi.assertNotVisible()
        retryUi.assertVisible()
    }

    fun clickRetry() {
        retryUi.click()
    }

    fun waitTillGone() {
        errorUi.waitTillDoesntExist()
    }

}
