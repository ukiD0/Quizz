package com.example.quizz.stats

import com.example.quizz.views.stats.StatsUiState

class GameOverViewModel(private val repository: StatsRepository) {
    fun statusUiState(): StatsUiState {
        val (corrects, incorrects) = repository.stats()
        return StatsUiState.Base(corrects, incorrects)
    }
}