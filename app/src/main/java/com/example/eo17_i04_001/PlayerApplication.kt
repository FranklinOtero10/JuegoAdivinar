package com.example.eo17_i04_001

import android.app.Application
import androidx.room.Room
import com.example.eo17_i04_001.database.PlayerDatabase

class PlayerApplication: Application() {

    companion object{
        lateinit var database: PlayerDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            PlayerDatabase::class.java,
            "PlayerDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}