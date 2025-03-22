package com.example.quizz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizz.game.GameScreen
import com.example.quizz.game.NavigateToGame
import com.example.quizz.stats.GameOverScreen
import com.example.quizz.stats.NavigateToGameOver

class MainActivity : AppCompatActivity(), Navigate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            navigateToGame()
    }

    override fun navigate(screen: Screen) = screen.show(R.id.container, supportFragmentManager)

}

interface Navigate : NavigateToGame, NavigateToGameOver {

    fun navigate(screen: Screen)

    override fun navigateToGameOver() = navigate(GameOverScreen)

    override fun navigateToGame() = navigate(GameScreen)
}


