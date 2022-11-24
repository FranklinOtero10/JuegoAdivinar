package com.example.eo17_i04_001.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eo17_i04_001.Interfaces.PlayerDao
import com.example.eo17_i04_001.Models.PlayerEntity

@Database(entities = [PlayerEntity::class], version = 1)
abstract class PlayerDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}