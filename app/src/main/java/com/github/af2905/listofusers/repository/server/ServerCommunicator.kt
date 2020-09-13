package com.github.af2905.listofusers.repository.server

import com.github.af2905.listofusers.repository.database.entity.UserEntity
import io.reactivex.Single

class ServerCommunicator(private val apiService: ApiService) {
    fun getUsers(): Single<List<UserEntity>> {
        return apiService.getUsers().map { return@map it.data }
    }
}