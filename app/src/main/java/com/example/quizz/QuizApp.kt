package com.example.quizz

import android.app.Application

class QuizApp : Application() {

    lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = GameViewModel(GameRepository.Base())
    }
}