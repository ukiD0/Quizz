package com.example.quizz

import android.content.SharedPreferences

interface IntCache {

    fun save(newValue: Int)

    fun read(): Int

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Int
    ) : IntCache {

        override fun save(newValue: Int) {
            sharedPreferences.edit().putInt(key, defaultValue).apply()
        }

        override fun read(): Int {
            return sharedPreferences.getInt(key, defaultValue)
        }

    }
}