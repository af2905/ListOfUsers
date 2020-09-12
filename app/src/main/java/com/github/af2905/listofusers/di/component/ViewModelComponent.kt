package com.github.af2905.listofusers.di.component

import com.github.af2905.listofusers.di.module.ViewModelModule
import com.github.af2905.listofusers.di.scope.ViewModelScope
import com.github.af2905.listofusers.presentation.views.activity.MainActivity
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [RepositoryComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
}