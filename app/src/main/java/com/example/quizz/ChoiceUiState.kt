package com.example.quizz

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton
import java.io.Serializable

interface ChoiceUiState : Serializable {

    fun update(secondChoiceButton: AppCompatButton)

    abstract class Abstract(
        private val value: String,
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true

    ) : ChoiceUiState {

        override fun update(button: AppCompatButton) = with(button) {
            text = value
            isEnabled = enabled
            isClickable = clickable
            setBackgroundColor(Color.parseColor(color))
        }
    }

    data class NotAvailableToChoose(private val text: String) :
        Abstract(text, "#75797E", enabled = false)

    data class AvailableToChoose(private val text: String) : Abstract(text, "#F18080", true)

    data class Correct(private val text: String) : Abstract(text, "#3AC13E")

    data class Incorrect(private val text: String) : Abstract(text, "#9C0E0E")

}