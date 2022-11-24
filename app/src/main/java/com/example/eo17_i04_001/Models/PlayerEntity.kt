package com.example.eo17_i04_001.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PlayerEntity")
class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var nickname: String,
    var intentos: Int) {

    fun getNombre(): String = this.nickname
    fun getInten(): Int = this.intentos

}