package com.example.quizz.stats

import com.example.quizz.ClearViewModel
import com.example.quizz.MyViewModel
import com.example.quizz.views.stats.StatsUiState

class GameOverViewModel(
    private val clearViewModel: ClearViewModel,
    private val repository: StatsRepository
) : MyViewModel {

    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (corrects, incorrects) = repository.stats()
            repository.clear()
            return StatsUiState.Base(corrects, incorrects)
        } else {
            StatsUiState.Empty
        }
    }
    fun clear() {
        clearViewModel.clear(GameOverViewModel::class.java)
    }
}