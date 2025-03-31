package com.example.quizz.stats.di

import com.example.quizz.IntCache
import com.example.quizz.di.AbstractProvideViewModel
import com.example.quizz.di.Core
import com.example.quizz.di.Module
import com.example.quizz.di.ProvideViewModel
import com.example.quizz.stats.GameOverViewModel
import com.example.quizz.stats.StatsRepository

class GameOverModule(private val core: Core) : Module<GameOverViewModel> {

    override fun viewModel(): GameOverViewModel {
        val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)

        return GameOverViewModel(
            core.clearViewModel,
            StatsRepository.Base(
                corrects,
                incorrects
            )
        )
    }

}

class ProvideGameOverViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, GameOverViewModel::class.java) {

    override fun module(): Module<*> = GameOverModule(core)
}