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
    private var choiceComputer = 0
    private var choiceHuman = 0
    private lateinit var result: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        buttonPaper.setOnClickListener {
            threwPaper()
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
        choiceHuman = 1
        game()
    }

    private fun threwScissors() {
        imageUser.setImageResource(R.drawable.scissors)
        choiceHuman = 2
        game()

    }

    private fun threwPaper() {
        imageUser.setImageResource(R.drawable.paper)
        choiceHuman = 3
        game()

    }


    private fun game() {

        choiceComputer = (1..3).random()

        when(choiceComputer) {
            1 -> imageComputer.setImageResource(R.drawable.paper)
            2 -> imageComputer.setImageResource(R.drawable.rock)
            3 -> imageComputer.setImageResource(R.drawable.scissors)
        }

        if (choiceHuman == 1 && choiceComputer == 1) {
            result = "Lost"
        } else if (choiceHuman == 1 && choiceComputer == 2) {
            result = "Draw"
        } else if (choiceHuman == 1 && choiceComputer == 3) {
            result = "Won"
        }

        if (choiceHuman == 2 && choiceComputer == 1) {
            result = "Won"
        } else if (choiceHuman == 2 && choiceComputer == 2) {
            result = "Lost"
        } else if (choiceHuman == 2 && choiceComputer == 3) {
            result = "Draw"
        }

        if (choiceHuman == 3 && choiceComputer == 1) {
            result = "Draw"
        } else if (choiceHuman == 3 && choiceComputer == 2) {
            result = "Won"
        } else if (choiceHuman == 3 && choiceComputer == 3) {
            result = "Lost"
        }


    }

}
