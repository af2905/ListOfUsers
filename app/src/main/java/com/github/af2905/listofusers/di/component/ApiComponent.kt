package com.github.af2905.listofusers.di.component

import com.github.af2905.listofusers.di.module.ApiModule
import com.github.af2905.listofusers.di.scope.ApiScope
import com.github.af2905.listofusers.repository.server.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}