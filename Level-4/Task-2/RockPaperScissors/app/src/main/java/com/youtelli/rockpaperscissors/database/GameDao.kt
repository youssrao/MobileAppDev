package com.youtelli.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.youtelli.rockpaperscissors.model.Game
import com.youtelli.rockpaperscissors.model.Statistics

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM gameTable")
    fun deleteGame()

    @Update
    suspend fun updateGame(game: Game)

    @Query("SELECT stats, COUNT(stats) AS total FROM gameTable")
    suspend fun getStatistics(): List<Statistics>


}