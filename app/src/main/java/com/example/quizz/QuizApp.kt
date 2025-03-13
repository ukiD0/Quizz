package com.example.quizz

import android.app.Application

class QuizApp : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = applicationContext.getSharedPreferences("quizAppData", MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPreferences, "indexKey", 0),
                IntCache.Base(sharedPreferences, "userChoiceIndexKey", -1),
            )
        )
    }
}