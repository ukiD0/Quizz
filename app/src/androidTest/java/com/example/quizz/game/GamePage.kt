package com.example.quizz.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher

class GamePage(
    question: String,
    private val choices: List<String>
) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val questionUi = QuestionUi(
        text = question,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    private val choicesUiList = choices.map {
        ChoiceUi(
            text = it,
            containerIdMatcher = containerIdMatcher,
            containerClassTypeMatcher = classTypeMatcher
        )
    }

    private val checkUi = ButtonUi(
        textResId = R.string.check,
        colorHex = "#80E1D3",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val nextUi = NextUi(
        textResId = R.string.next,
        colorHex = "#B776D7",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    fun assertAskedQuestionState() {
        questionUi.assertTextVisible()
        choicesUiList.forEach {
            it.assertAvalibleToChooseState()
        }
        checkUi.assertNotVisible()
        nextUi.assertNotVisible()
    }

    fun clickFirstChoice() {
        choicesUiList.first().click()
    }

    fun assertFirstChoiceMakeState() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertNotAvalibleToChooseState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertNotAvalibleToChooseState()
        }
        checkUi.assertVisible()
        checkUi.assertNotVisible()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertAnswerCheckStateFirstIsCorrect() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertCorrectState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertNotAvalibleToChooseState()
        }
        checkUi.assertVisible()
        checkUi.assertNotVisible()
    }

    fun clickSecondChoice() {
        choicesUiList[1].click()
    }

    fun assertSecondChoiceMakeState() {
        questionUi.assertTextVisible()
        choicesUiList.forEachIndexed { index, choiceUi ->
            if (index == 1) {
                choiceUi.assertNotAvalibleToChooseState()
            } else {
                choiceUi.assertAvalibleToChooseState()
            }
        }
        checkUi.assertVisible()
        checkUi.assertNotVisible()
    }

    fun assertAnswerCheckStateFirstIsCorrectSecondIsCorrect() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertCorrectState()
        choicesUiList[1].assertIncorrectState()
        choicesUiList[2].assertNotAvalibleToChooseState()
        choicesUiList[3].assertNotAvalibleToChooseState()
        checkUi.assertNotVisible()
        checkUi.assertVisible()
    }

    fun clickNext() {
        nextUi.click()
    }

}