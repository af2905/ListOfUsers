package com.github.af2905.listofusers.di.module

import com.github.af2905.listofusers.di.scope.RepositoryScope
import com.github.af2905.listofusers.repository.AppRepository
import com.github.af2905.listofusers.repository.database.AppDatabase
import com.github.af2905.listofusers.repository.server.ServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    fun providesRepository(communicator: ServerCommunicator, db: AppDatabase): AppRepository {
        return AppRepository(communicator, db)
    }
}