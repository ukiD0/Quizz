package com.example.quizz.di

import com.example.quizz.MyViewModel

interface Module<T : MyViewModel> {
    fun viewModel(): T
}