package com.github.af2905.listofusers.di.component

import com.github.af2905.listofusers.di.module.DatabaseModule
import com.github.af2905.listofusers.repository.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}