package com.github.af2905.listofusers.di.module

import com.github.af2905.listofusers.repository.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(val appDatabase: AppDatabase) {
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }
}