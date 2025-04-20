package com.example.quizz.load.data

import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL

interface LoadRepository {

    fun load(resultCallback: (LoadResult) -> Unit)

    class Base(
        private val parseQuestionAndChoices: ParseQuestionAndChoices,
        private val dataCache: StringCache,
    ) : LoadRepository {

        private val url = "https://opentdb.com/api.php?amount=10&type=multiple"

        override fun load(resultCallback: (LoadResult) -> Unit) {
            val connection = URL(url).openConnection() as HttpURLConnection
            try {
                val data: String = connection.inputStream.bufferedReader().use { it.readText() }
                val response = parseQuestionAndChoices.parse(data)
                if (response.response_code == 0) {
                    val list = response.results
                    if (list.isEmpty()) {
                        resultCallback.invoke(LoadResult.Error("empty data try again later"))
                    } else {
                        dataCache.save(data)
                        resultCallback.invoke(LoadResult.Success)
                    }
                } else {
                    resultCallback.invoke(LoadResult.Error(handleResponseCode(response.response_code)))
                }

            } catch (e: Exception) {
                resultCallback.invoke(LoadResult.Error(e.message ?: "error"))
            } finally {
                connection.disconnect()
            }
        }

        private fun handleResponseCode(code: Int): String {
            return when (code) {
                1 -> "No result"
                2 -> "Invalid Parameter contains an invalid parameter"
                3 -> "Token not found"
                4 -> "Token Empty"
                5 -> "Rate limit Too many requests"
                else -> ""
            }
        }

    }
}

interface ParseQuestionAndChoices {
    fun parse(source: String): Response

    class Base(
        private val gson: Gson
    ) : ParseQuestionAndChoices {

        override fun parse(source: String): Response {
            return gson.fromJson(source, Response::class.java)
        }

    }
}

data class Response(
    val response_code: Int,
    val results: List<QuestionAnfChoicesCloud>
)

data class QuestionAnfChoicesCloud(
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)
