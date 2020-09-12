package com.github.af2905.listofusers

import android.app.Application
import androidx.room.Room
import com.github.af2905.listofusers.di.component.*
import com.github.af2905.listofusers.di.module.ApiModule
import com.github.af2905.listofusers.di.module.DatabaseModule
import com.github.af2905.listofusers.di.module.RepositoryModule
import com.github.af2905.listofusers.di.module.ViewModelModule
import com.github.af2905.listofusers.repository.database.AppDatabase

class App : Application() {
    private lateinit var db: AppDatabase
    private lateinit var viewModelComponent: ViewModelComponent

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    private fun initRoom() {
        db = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(this.db))
            .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .repositoryComponent(repositoryComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this.viewModelComponent
    }
}