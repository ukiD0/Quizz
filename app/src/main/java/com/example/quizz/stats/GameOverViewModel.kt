package com.example.quizz.stats

import com.example.quizz.views.stats.StatsUiState

class GameOverViewModel(private val repository: StatsRepository) {

    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (corrects, incorrects) = repository.stats()
            repository.clear()
            return StatsUiState.Base(corrects, incorrects)
        } else {
            StatsUiState.Empty
        }
    }
}