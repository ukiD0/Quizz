package com.example.quizz

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton

interface ChoiceUiState {

    fun update(secondChoiceButton: AppCompatButton)

    abstract class Abstract(
        private val value: String,
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true

    ) : ChoiceUiState {

        override fun update(button: AppCompatButton) = with(button) {
            text = value
            if (enabled)
                setBackgroundColor(Color.parseColor(color))
            isEnabled = enabled
            isClickable = clickable
        }
    }

    data class NotAvailableToChoose(private val text: String) : Abstract(text, "", enabled = false)

    data class AvailableToChoose(private val text: String) : Abstract(text, "#F18080", true)

    data class Correct(private val text: String) : Abstract(text, "#3AC13E")

    data class Incorrect(private val text: String) : Abstract(text, "#9C0E0E")

}