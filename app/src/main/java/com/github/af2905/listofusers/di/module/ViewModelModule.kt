package com.github.af2905.listofusers.di.module

import android.app.Application
import com.github.af2905.listofusers.App
import com.github.af2905.listofusers.di.scope.ViewModelScope
import com.github.af2905.listofusers.repository.AppRepository
import com.github.af2905.listofusers.viewmodel.AllUsersViewModel
import com.github.af2905.listofusers.viewmodel.SingleUserViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {
    var app: Application = app

    @ViewModelScope
    @Provides
    fun providesAllUserViewModel(repository: AppRepository): AllUsersViewModel {
        return AllUsersViewModel(app, repository)
    }

    @ViewModelScope
    @Provides
    fun providesSingleUserViewModel(repository: AppRepository): SingleUserViewModel {
        return SingleUserViewModel(app, repository)
    }
}