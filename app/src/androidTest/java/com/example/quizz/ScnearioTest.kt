package com.example.quizz

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ScnearioTest {

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

        gamePage.clickFirstChoise()
        gamePage.assertFirstChoiseMakeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckStateFirstIsCorrect()
    }
    /**
     * Test case number 2
     */
    @Test
    fun caseNumber2(){
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoise()
        gamePage.assertFirstChoiseMakeState()

        gamePage.clickSecondChoise()
        gamePage.assertSecondChoiseMakeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckStateFirstIsCorrectSecondIsCorrect()

        gamePage.clicNext()

        gamePage = GamePage(
            question = "What color is the grass?", choices = listOf(
            "green","blue","red","yellow"
            )
        )
        gamePage.assertAskedQuestionState()
    }
}