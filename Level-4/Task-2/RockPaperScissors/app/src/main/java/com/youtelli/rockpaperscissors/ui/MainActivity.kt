package com.youtelli.rockpaperscissors.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.youtelli.rockpaperscissors.R
import com.youtelli.rockpaperscissors.database.GameRepository
import com.youtelli.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

//TODO: - display all time statistics


const val ROCK = "rock"
const val PAPER = "paper"
const val SCISSORS = "scissors"

const val WON = "WON"
const val LOST = "LOST"
const val DRAW = "DRAW"
class MainActivity : AppCompatActivity() {

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private lateinit var gameRepository: GameRepository
    private lateinit var history: HistoryActivity
    private lateinit var choiceComputer: String
    private lateinit var choiceHuman: String
    private lateinit var result: String
    private var stats = 0
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        gameRepository = GameRepository(this)
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

    private fun getGamesFromDatabase() {
        mainScope.launch {
            val games = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }

            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            this@MainActivity.gameAdapter.notifyDataSetChanged()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun threwRock() {
        imageUser.setImageResource(R.drawable.rock)
        game(ROCK)
        getStatistics(ROCK)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun threwScissors() {
        imageUser.setImageResource(R.drawable.scissors)
        game(SCISSORS)
        getStatistics(SCISSORS)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun threwPaper() {
        imageUser.setImageResource(R.drawable.paper)
        game(PAPER)
        getStatistics(PAPER)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun game(humanChoice: String) {

        choiceHuman = humanChoice

        when ((1..3).random()) {
            1 -> {
                choiceComputer = PAPER
                imageComputer.setImageResource(R.drawable.paper)
            }
            2 -> {
                choiceComputer = ROCK
                imageComputer.setImageResource(R.drawable.rock)
            }
            3 -> {
                choiceComputer = SCISSORS
                imageComputer.setImageResource(R.drawable.scissors)
            }
        }


        if (choiceHuman == ROCK && choiceComputer == PAPER) {
            result = LOST
        } else if (choiceHuman == ROCK && choiceComputer == ROCK) {
            result = DRAW
        } else if (choiceHuman == ROCK && choiceComputer == SCISSORS) {
            result = WON
        }

        if (choiceHuman == SCISSORS && choiceComputer == PAPER) {
            result = WON
        } else if (choiceHuman == SCISSORS && choiceComputer == ROCK) {
            result = LOST
        } else if (choiceHuman == SCISSORS && choiceComputer == SCISSORS) {
            result = DRAW
        }

        if (choiceHuman == PAPER && choiceComputer == PAPER) {
            result = DRAW
        } else if (choiceHuman == PAPER && choiceComputer == ROCK) {
            result = WON
        } else if (choiceHuman == PAPER && choiceComputer == SCISSORS) {
            result = LOST
        }

        textResult.text = result

        when (result) {
            WON -> {
                getStatistics(WON)
            }
            DRAW -> {
                getStatistics(DRAW)
            }
            LOST -> {
                getStatistics(LOST)
            }
        }


        val timeStamp = ZonedDateTime.now()
        val format =  DateTimeFormatter.RFC_1123_DATE_TIME

        games.add(Game(result, format.format(timeStamp).toString() ,choiceHuman, choiceComputer, stats))


    }

    private fun getStatistics(result: String) {
        mainScope.launch {
            val gameStats = withContext(Dispatchers.IO) {
                gameRepository.getStatistics()
            }

            when (result) {
                WON -> {
                    stats = 0
                }
                DRAW -> {
                    stats = 1
                }
                LOST -> {
                    stats = 2
                }
            }

            var win = 0
            var draw = 0
            var lose = 0
            for (stat in gameStats) {
                if (stat.stats == 0) {
                    win = stat.total
                }
                if (stat.stats == 1) {
                    draw = stat.total
                }
                if (stat.stats == 2) {
                    lose = stat.total
                }
            }


            tvStats.text = getString(R.string.stats, win, draw, lose)
        }
    }

    override fun onStart() {
        super.onStart()
        games.clear()
    }


    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    val game = data!!.getParcelableExtra<Game>(EXTRA_HISTORY)

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            history.historyGameRepository.insertGame(game)
                        }
                        history.getGamesFromDatabase()
                    }


                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun startHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        intent.putParcelableArrayListExtra("mylist", games)
        // intent.putExtra(EXTRA_GAMES, games)
        startActivityForResult(intent, 100)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        startHistory()
        // automatically h
        // ,,,andle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_get_history -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
