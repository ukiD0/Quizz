package com.example.quizz

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()

}