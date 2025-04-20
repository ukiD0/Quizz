package com.example.quizz.game.di

import com.example.quizz.di.AbstractProvideViewModel
import com.example.quizz.di.Core
import com.example.quizz.di.Module
import com.example.quizz.di.ProvideViewModel
import com.example.quizz.load.data.LoadRepository
import com.example.quizz.load.data.ParseQuestionAndChoices
import com.example.quizz.load.data.Response
import com.example.quizz.load.data.StringCache
import com.example.quizz.load.presentation.LoadViewModel
import com.example.quizz.load.presentation.UiObservable

class ProvideLoadViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, LoadViewModel::class.java) {
    override fun module(): Module<*> = LoadModule(core)
}

class LoadModule(private val core: Core) : Module<LoadViewModel> {
    override fun viewModel(): LoadViewModel {
        val responseDefault = Response(-1, emptyList())
        val defaultResponse = core.gson.toJson(responseDefault)
        return LoadViewModel(
            LoadRepository.Base(
                ParseQuestionAndChoices.Base(core.gson),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse)
            ),
            UiObservable.Base()
        )
    }

}