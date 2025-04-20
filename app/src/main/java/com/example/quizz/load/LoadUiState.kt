package com.example.quizz.load

import com.example.quizz.R
import com.example.quizz.game.NavigateToGame
import com.example.quizz.views.error.ErrorUiState
import com.example.quizz.views.error.UpdateError
import com.example.quizz.views.visibleButton.UpdateVisibility
import com.example.quizz.views.visibleButton.VisibilityUiState

interface LoadUiState {

    fun show(
        errorTextView: UpdateError,
        retruButton: UpdateVisibility,
        progressBar: UpdateVisibility
    )

    fun navigate(navigateToGame: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorUiState: ErrorUiState,
        private val retryUiState: VisibilityUiState,
        private val progressUiState: VisibilityUiState
    ) : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retruButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            errorTextView.update(errorUiState)
            retruButton.update(retryUiState)
            progressBar.update(progressUiState)
        }
    }

    object Progress : Abstract(ErrorUiState.Hide, VisibilityUiState.Gone, VisibilityUiState.Visible)

    object Success : Abstract(ErrorUiState.Hide, VisibilityUiState.Gone, VisibilityUiState.Gone) {
        override fun navigate(navigateToGame: NavigateToGame) {
            navigateToGame.navigateToGame()
        }
    }

    data class Error(private val message: String) : Abstract(
        ErrorUiState.Show(R.string.no_internet_connection),
        VisibilityUiState.Visible, VisibilityUiState.Gone
    )//todo handle error message
}