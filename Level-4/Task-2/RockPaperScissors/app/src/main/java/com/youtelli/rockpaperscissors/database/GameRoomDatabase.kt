package com.youtelli.rockpaperscissors.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.youtelli.rockpaperscissors.model.Game

@Database(entities = [Game::class], version = 2, exportSchema = false)
abstract class GameRoomDatabase : RoomDatabase(){

    abstract fun gameDao(): GameDao


    companion object{
        private const val DATABASE_NAME = "GAME_DATABASE"
        @Volatile
        private var gameRoomDatabaseInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomDatabaseInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (gameRoomDatabaseInstance == null) {
                        gameRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java,
                            DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return gameRoomDatabaseInstance
        }

    }

}