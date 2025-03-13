package com.example.quizz

import android.view.View
import com.example.quizz.databinding.ActivityMainBinding
import java.io.Serializable

interface GameUiState : Serializable {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val questionText: String,
        private val choicesStateList: List<ChoiceUiState>,
        private val checkVisibility: Int,
        private val nextVisibility: Int
    ) : GameUiState {
        override fun update(binding: ActivityMainBinding) = with(binding) {
            questionTextView.text = questionText
            choicesStateList[0].update(firstChoiceButton)
            choicesStateList[1].update(secondChoiceButton)
            choicesStateList[2].update(thirdChoiceButton)
            choicesStateList[3].update(forthChoiceButton)
            checkButton.visibility = checkVisibility
            nextButton.visibility = nextVisibility
        }
    }

    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : Abstract(
        question,
        choices.map { ChoiceUiState.AvailableToChoose(it) },
        View.INVISIBLE,
        View.INVISIBLE
    )


    data class ChoiceMade(
        private val question: String,
        private val choices: List<ChoiceUiState>
    ) : Abstract(
        question,
        choices,
        View.VISIBLE,
        View.INVISIBLE
    )

    data class AnswerChecked(
        private val question: String,
        private val choices: List<ChoiceUiState>
    ) : Abstract(
        question,
        choices,
        View.INVISIBLE,
        View.VISIBLE
    )
}

