package com.example.quizz.views.choice

import java.io.Serializable

interface ChoiceUiState : Serializable {

    fun update(update: UpdateChoiceButton)

    abstract class Abstract(
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true
    ) : ChoiceUiState {

        override fun update(update: UpdateChoiceButton) {
            update.update(color, clickable, enabled)
        }
    }

    object NotAvailableToChoose : Abstract("#75797E", enabled = false)

    data class Initial(private val text: String) : Abstract("#F18080", true) {
        override fun update(update: UpdateChoiceButton) {
            super.update(update)
            update.update(text)
        }
    }

    object AvailableToChoose : Abstract("#F18080", true)

    object Correct : Abstract("#3AC13E")

    object Incorrect : Abstract("#9C0E0E")

}