package com.example.quizz.di

import com.example.quizz.MyViewModel
import com.example.quizz.game.di.ProvideGameViewModel
import com.example.quizz.stats.di.ProvideGameOverViewModel

interface ProvideViewModel {
    fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T

    class Make(
        core: Core
    ) : ProvideViewModel {

        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
        }

        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T =
            chain.makeViewModel(clazz)
    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
            throw IllegalStateException("unknown class $clazz")
        }
    }
}