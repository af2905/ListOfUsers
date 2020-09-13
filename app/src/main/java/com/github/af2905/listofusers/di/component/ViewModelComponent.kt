package com.github.af2905.listofusers.di.component

import com.github.af2905.listofusers.di.module.ViewModelModule
import com.github.af2905.listofusers.di.scope.ViewModelScope
import com.github.af2905.listofusers.presentation.views.fragments.AllUsersFragment
import com.github.af2905.listofusers.presentation.views.fragments.SingleUserFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [RepositoryComponent::class])
interface ViewModelComponent {
    fun inject(fragment: AllUsersFragment)
    fun inject(fragment: SingleUserFragment)
}