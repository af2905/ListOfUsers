package com.github.af2905.listofusers

import android.app.Application
import androidx.room.Room
import com.github.af2905.listofusers.repository.database.AppDatabase

class App : Application() {
    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        initRoom()
    }

    private fun initRoom() {
        db = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }
}