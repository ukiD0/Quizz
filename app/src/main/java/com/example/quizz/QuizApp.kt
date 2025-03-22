package com.example.quizz

import android.app.Application
import android.content.Context
import com.example.quizz.game.GameRepository
import com.example.quizz.game.GameViewModel
import com.example.quizz.stats.GameOverViewModel

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

