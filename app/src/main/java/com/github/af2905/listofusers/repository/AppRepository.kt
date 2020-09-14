package com.github.af2905.listofusers.repository

import com.github.af2905.listofusers.repository.database.AppDatabase
import com.github.af2905.listofusers.repository.database.entity.UserEntity
import com.github.af2905.listofusers.repository.server.ServerCommunicator
import io.reactivex.Single

class AppRepository(private val communicator: ServerCommunicator, private val db: AppDatabase) {
    fun getAllUsersFromNetwork(): Single<List<UserEntity>> {
        return communicator.getUsers().flatMap {
            db.userDao().insertList(it)
            getAllUsersFromDatabase()
        }
    }

    fun getAllUsersFromDatabase(): Single<List<UserEntity>> {
        return Single.just(db.userDao().getAll())
    }

    fun updateAllUsersInDatabase(): Single<Unit> {
        return communicator.getUsers().flatMap { Single.just(db.userDao().updateAll(it)) }
    }

    fun getSingleUserFromDatabase(id: Int): Single<UserEntity> {
        return Single.just(db.userDao().getById(id))
    }

    fun updateSingleUserInDatabase(userEntity: UserEntity): Single<Unit> {
        return Single.just(db.userDao().update(userEntity))
    }

    fun deleteSingleUserFromDatabase(userEntity: UserEntity): Single<Unit> {
        return Single.just(db.userDao().delete(userEntity))
    }
}