package com.example.quizz.load


interface UiObservable {
    fun register(observer: (LoadUiState) -> Unit)

    fun unregister()

    fun postUiState(uiState: LoadUiState)

    class Base : UiObservable {

        private var uiStateCached: LoadUiState? = null //progress success fail

        private var observerCached: ((LoadUiState) -> Unit)? = null //like fragment

        override fun register(observer: (LoadUiState) -> Unit) { //onResume
            observerCached = observer
            if (uiStateCached != null) {
                observerCached!!.invoke(uiStateCached!!) //update ui
                uiStateCached = null
            }
        }

        override fun unregister() { //onPause
            observerCached = null
        }
        /*
        1. Register like fragment onResume
        2. some time lasted
        3. posUiState -> immediately update ui (no caching)
         */

        /*
        1. register like fragment onResume
        2. some time lasted
        3. unregister like fragment onPause
        4. some time lasted
        5. postUiState : cache ui state and wait till register aka onResume new fragment
        6. register new fragment like onResume : update ui now! and clear the cache
         */
        override fun postUiState(uiState: LoadUiState) {//pinged by viewmodel asynchronously
            if (observerCached == null) { //onpause was called but onresume still not
                uiStateCached = uiState //save ui state till new fragment become onresume
            } else {
                observerCached!!.invoke(uiState) //update ui after onresume and till onpause
                uiStateCached = null
            }
        }

    }
}