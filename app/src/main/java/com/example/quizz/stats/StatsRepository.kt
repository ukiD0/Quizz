package com.example.quizz.stats

interface StatsRepository {

    fun stats(): Pair<Int, Int>


}