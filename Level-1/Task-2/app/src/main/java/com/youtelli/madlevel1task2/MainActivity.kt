package com.youtelli.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.youtelli.madlevel1task2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button.setOnClickListener {
            checkAnswer()
        }


    }



    fun checkAnswer() {
        var input1 = findViewById<EditText>(R.id.input1)
        var input2 = findViewById<EditText>(R.id.input2)
        var input3 = findViewById<EditText>(R.id.input3)
        var input4 = findViewById<EditText>(R.id.input4)

        val inputAnswer1 = input1.text.toString()
        val inputAnswer2 = input2.text.toString()
        val inputAnswer3 = input3.text.toString()
        val inputAnswer4 = input4.text.toString()

        if (TextUtils.isEmpty(inputAnswer1) || TextUtils.isEmpty(inputAnswer2) ||
            TextUtils.isEmpty(inputAnswer3) || TextUtils.isEmpty(inputAnswer4)) {
            Toast.makeText(applicationContext, R.string.emptyAnswers, Toast.LENGTH_LONG).show()
        }
        else if (inputAnswer1 == getString(R.string.T) && inputAnswer2 == getString(R.string.F) &&
            inputAnswer3 == getString(R.string.F) && inputAnswer4 == getString(R.string.F)) {
            Toast.makeText(applicationContext, R.string.correctAnswers, Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(applicationContext, R.string.incorrectAnswers, Toast.LENGTH_LONG).show()
        }

    }

}
