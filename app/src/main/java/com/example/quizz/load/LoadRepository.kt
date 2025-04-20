package com.example.quizz.load

interface LoadRepository {

    fun load(resultCallback: (LoadResult) -> Unit)
}
