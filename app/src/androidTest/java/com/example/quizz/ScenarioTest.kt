package com.example.quizz

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizz.game.GamePage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage : GamePage

    @Before
    fun setup(){
        gamePage = GamePage(
            question = "What color is the sky?", choices = listOf(
                "blue","green","red","yellow"
            )
        )
    }


    /**
     * Test case number 1
     */
    @Test
    fun caseNumber1() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMakeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMakeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckStateFirstIsCorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckStateFirstIsCorrect()

    }
    /**
     * Test case number 2
     */
    @Test
    fun caseNumber2(){
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()


        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMakeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMakeState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMakeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSecondChoiceMakeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckStateFirstIsCorrectSecondIsCorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckStateFirstIsCorrectSecondIsCorrect()

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color is the grass?", choices = listOf(
            "green","blue","red","yellow"
            )
        )
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()
    }
}