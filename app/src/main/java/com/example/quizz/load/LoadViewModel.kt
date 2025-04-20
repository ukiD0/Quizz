package com.example.quizz.load

import com.example.quizz.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable
) : MyViewModel {

    private val callback: (LoadResult) -> Unit = { p1 ->
        observable.postUiState(
            if (p1.isSuccessful())
                LoadUiState.Success
            else
                LoadUiState.Error(p1.message())
        )
    }

    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            repository.load(callback)
        }
    }

    fun startUpdates(observer: (LoadUiState) -> Unit) = observable.register(observer)

    fun stopUpdates() = observable.unregister()

}