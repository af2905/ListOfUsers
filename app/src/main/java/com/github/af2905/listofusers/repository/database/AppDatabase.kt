package com.github.af2905.listofusers.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.af2905.listofusers.repository.database.dao.UserDao
import com.github.af2905.listofusers.repository.database.entity.UserEntity


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}