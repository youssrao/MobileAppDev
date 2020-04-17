package com.youtelli.rockpaperscissors.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.youtelli.rockpaperscissors.R
import com.youtelli.rockpaperscissors.database.GameRepository
import com.youtelli.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var gameRepository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        buttonPaper.setOnClickListener {
            threwRock()
        }

        buttonRock.setOnClickListener {
            threwRock()
        }

        buttonScissors.setOnClickListener {
            threwScissors()
        }
    }


    private fun threwRock() {
        imageUser.setImageResource(R.drawable.rock)
    }

    private fun threwScissors() {
        imageUser.setImageResource(R.drawable.scissors)

    }

    private fun threwPaper() {
        imageUser.setImageResource(R.drawable.paper)

    }

}
