package com.youtelli.rockpaperscissors.database

import android.content.Context
import com.youtelli.rockpaperscissors.model.Game
import com.youtelli.rockpaperscissors.model.Statistics

class GameRepository (context: Context){
    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGames() {
        gameDao.deleteGame()
    }


    suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }

    suspend fun getStatistics(): List<Statistics> {
        return gameDao.getStatistics()
    }




}