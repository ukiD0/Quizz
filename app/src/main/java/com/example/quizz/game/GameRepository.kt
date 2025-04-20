package com.example.quizz.game

import com.example.quizz.IntCache
import com.example.quizz.load.ParseQuestionAndChoices
import com.example.quizz.load.StringCache

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()
    fun isLastQuestion(): Boolean
    fun clear()

    class Base(
        private val corrects: IntCache,
        private val incorrect: IntCache,
        private val index: IntCache,
        private val userChoiceIndex: IntCache,
        private val list: List<QuestionAndChoices> = listOf(
            QuestionAndChoices(
                question = "What color is the sky?",
                choices = listOf("blue", "green", "red", "yellow"),
                correctIndex = 0
            ),
            QuestionAndChoices(
                question = "What color is the grass?",
                choices = listOf("green", "blue", "red", "yellow"),
                correctIndex = 0
            )
        )
    ) : GameRepository {

        constructor(
            corrects: IntCache,
            incorrect: IntCache,
            index: IntCache,
            userChoiceIndex: IntCache,
            dataCache: StringCache,
            parseQuestionAndChoices: ParseQuestionAndChoices
        ) : this(
            corrects,
            incorrect,
            index,
            userChoiceIndex,
            parseQuestionAndChoices.parse(dataCache.read()).results.map {
                val list = mutableListOf<String>()
                list.add(it.correct_answer)
                list.add(it.incorrect_answers)
                val finalList = list.shuffled()
                val indexOfCorrect = finalList.indexOf(it.correct_answer)
                QuestionAndChoices(
                    it.question,
                    finalList,
                    indexOfCorrect
                )
                QuestionAndChoices(
                    it.question,
                    listOf(it.correct_answer) + it.incorrect_answers,
                    0
                )
            }
        )

        override fun questionAndChoices(): QuestionAndChoices {
            return list[index.read()]
        }

        override fun saveUserChoice(index: Int) {
            userChoiceIndex.save(index)
        }

        override fun check(): CorrectAndUserChoiceIndexes {
            val correctIndex = questionAndChoices().correctIndex

            if (userChoiceIndex.read() == correctIndex) {
                corrects.save(corrects.read() + 1)
            } else {
                incorrect.save(incorrect.read() + 1)
            }
            return CorrectAndUserChoiceIndexes(
                correctIndex = correctIndex,
                userChoiceIndex = userChoiceIndex.read()
            )
        }

        override fun next() {
            userChoiceIndex.save(-1)
            index.save(index.read() + 1)
        }

        override fun isLastQuestion() = index.read() == list.size

        override fun clear() {
            userChoiceIndex.save(-1)
            index.save(0)
        }

    }

}