package com.example.quizz.game.di

import com.example.quizz.IntCache
import com.example.quizz.di.AbstractProvideViewModel
import com.example.quizz.di.Core
import com.example.quizz.di.Module
import com.example.quizz.di.ProvideViewModel
import com.example.quizz.game.GameRepository
import com.example.quizz.game.GameViewModel
import com.example.quizz.load.data.ParseQuestionAndChoices
import com.example.quizz.load.data.Response
import com.example.quizz.load.data.StringCache

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel(): GameViewModel {
        val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)
        val responseDefault = Response(-1, emptyList())
        val defaultResponse = core.gson.toJson(responseDefault)
        return GameViewModel(
            core.clearViewModel,
            GameRepository.Base(
                corrects,
                incorrects,
                IntCache.Base(core.sharedPreferences, "indexKey", 0),
                IntCache.Base(core.sharedPreferences, "userChoiceIndexKey", -1),
                StringCache.Base(core.sharedPreferences, "response_data", defaultResponse),
                ParseQuestionAndChoices.Base(core.gson)
            )
        )
    }
}

class ProvideGameViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, GameViewModel::class.java) {
    override fun module(): Module<*> = GameModule(core)
}