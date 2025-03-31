package com.example.quizz.di

import android.content.Context
import com.example.quizz.ClearViewModel

class Core(context: Context, val clearViewModel: ClearViewModel) {
    val sharedPreferences = context.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
}