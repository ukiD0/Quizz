package com.example.quizz

import com.example.quizz.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding): Unit = throw IllegalStateException("")

    data class AskedQuestion(
        val question: String,
        val choices: List<String>
    ) :
        GameUiState


    data class ChoiceMade(
        val question: String,
        val choices: List<ChoiceUiState>
    ) :
        GameUiState

    data class AnswerChecked(
        val question: String,
        val choices: List<ChoiceUiState>
    ) :
        GameUiState
}

interface ChoiceUiState {

    data class NotAvailableToChoose(val text: String) : ChoiceUiState

    data class AvailableToChoose(val text: String) : ChoiceUiState

    data class Correct(val text: String) : ChoiceUiState

    data class Incorrect(val text: String) : ChoiceUiState

}