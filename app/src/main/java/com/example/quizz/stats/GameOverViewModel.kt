package com.example.quizz.stats

import com.example.quizz.views.stats.StatsUiState

class GameOverViewModel {
    fun statusUiState(): StatsUiState {
        return StatsUiState.Base(1, 1)
    }
}