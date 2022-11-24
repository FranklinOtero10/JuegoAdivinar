package com.example.eo17_i04_001.Interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.eo17_i04_001.Models.PlayerEntity

@Dao
interface PlayerDao {

    @Query("SELECT * FROM PlayerEntity")
    fun getAll(): MutableList<PlayerEntity>

    @Query("SELECT * FROM PlayerEntity order by intentos asc")
    fun getAltos(): MutableList<PlayerEntity>

    @Query("SELECT * FROM PlayerEntity where nickname =  :nick")
    fun comprobar(nick: String): Boolean

    @Query("UPDATE PlayerEntity SET intentos = :pIntentos WHERE nickname = :pNick")
    fun updateScore(pIntentos: Int, pNick: String)

    @Update
    fun update(playerEntity: PlayerEntity)

    @Insert
    fun addPlayer(playerEntity: PlayerEntity)
}