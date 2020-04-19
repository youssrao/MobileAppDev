package com.youtelli.rockpaperscissors.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gameTable")
data class Game (

    @ColumnInfo(name = "game")
    var gameResult: String,
    var gameTime: String,
    var gameHuman: String,
    var gameComputer: String,

    @ColumnInfo(name = "stats")
    var stats: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable