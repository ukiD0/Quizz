package com.example.quizz

import android.app.Application
import com.example.quizz.di.Core
import com.example.quizz.di.ManageViewModels
import com.example.quizz.di.ProvideViewModel

class QuizApp : Application(), ProvideViewModel {

    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()
        val clearViewModel = object : ClearViewModel {
            override fun clear(viewModelClass: Class<out MyViewModel>) =
                factory.clear(viewModelClass)
        }
        val make = ProvideViewModel.Make(
            Core(
                this,
                clearViewModel
            )
        )
        factory = ManageViewModels.Factory(make)
    }

    override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T = factory.makeViewModel(clazz)
}

