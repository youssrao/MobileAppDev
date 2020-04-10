package com.youtelli.madlevel1task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.youtelli.madlevel1task1.databinding.ActivityHigherLowerBinding
import kotlinx.android.synthetic.main.activity_higher_lower.*

class HigherLowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    /**
     * Set the initial (UI) state of the game.
     */
    private fun initViews() {
        btnEqual.setOnClickListener {
            onEqualClick()
        }

        btnHigher.setOnClickListener {
            onHigherClick()
        }

        btnLower.setOnClickListener {
            onLowerClick()
        }


        updateUI()
    }

    /**
     * Update the last throw text and the dice image resource drawable with the current throw.
     */
    private fun updateUI() {
        binding.textViewLastThrow.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> binding.imageViewDice.setImageResource(R.drawable.dice1)
            2 -> binding.imageViewDice.setImageResource(R.drawable.dice2)
            3 -> binding.imageViewDice.setImageResource(R.drawable.dice3)
            4 -> binding.imageViewDice.setImageResource(R.drawable.dice4)
            5 -> binding.imageViewDice.setImageResource(R.drawable.dice5)
            6 -> binding.imageViewDice.setImageResource(R.drawable.dice6)
        }
    }

    /**
     * Replaces the previous dice value with the current one and replaces the current dice with a
     * new dice with a random number between 1 and 6 (inclusive).
     */
    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
    }

    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
    }

    private fun onHigherClick() {
        rollDice()

        if (currentThrow > lastThrow)
            onAnswerCorrect()
        else onAnswerIncorrect()

    }

    private fun onLowerClick() {
        rollDice()

        if (currentThrow < lastThrow)
            onAnswerCorrect()
        else onAnswerIncorrect()

    }

    private fun onEqualClick() {
        rollDice()

        if (currentThrow == lastThrow)
            onAnswerCorrect()
        else onAnswerIncorrect()

    }


}
