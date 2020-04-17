package com.youtelli.rockpaperscissors

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(reminder: Game)

    @Query("DELETE FROM gameTable")
    fun deleteGame()

    @Update
    suspend fun updateGame(reminder: Game)


}