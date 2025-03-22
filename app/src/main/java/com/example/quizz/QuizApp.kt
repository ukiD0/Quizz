package com.example.quizz

import android.app.Application
import android.content.Context
import com.example.quizz.views.stats.StatsUiState

class QuizApp : Application() {

    lateinit var viewModel: GameViewModel
    lateinit var gameOverViewModel: GameOverViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPreferences, "indexKey", 0),
                IntCache.Base(sharedPreferences, "userChoiceIndexKey", -1),
            )
        )
        gameOverViewModel = GameOverViewModel()
    }
}

class GameOverViewModel {
    fun statusUiState(): StatsUiState {
        return StatsUiState.Base(1, 1)
    }
}
