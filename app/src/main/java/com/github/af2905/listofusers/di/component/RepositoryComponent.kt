package com.github.af2905.listofusers.di.component

import com.github.af2905.listofusers.di.module.RepositoryModule
import com.github.af2905.listofusers.di.scope.RepositoryScope
import com.github.af2905.listofusers.repository.AppRepository
import dagger.Component

@RepositoryScope
@Component(
    modules = [RepositoryModule::class],
    dependencies = [ApiComponent::class, DatabaseComponent::class]
)
interface RepositoryComponent {
    val repository: AppRepository
}