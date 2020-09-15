package com.github.af2905.listofusers

import com.github.af2905.listofusers.repository.database.entity.UserEntity
import io.reactivex.Single
import io.reactivex.observers.TestObserver

class TestHelper {

    fun createListOfUsers(): List<UserEntity> {
        val users = mutableListOf<UserEntity>()
        for (i in 1..100) {
            users.add(
                UserEntity(
                    i, "email$i", "name$i", "lastName$i"
                )
            )
        }
        return users
    }

    fun <T> checkDataFlow(source: Single<List<T>>, list: List<T>, observer: TestObserver<List<T>>) {
        observer.assertNotSubscribed()
        source.subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertComplete()
        observer.assertNoErrors()
        observer.assertValueCount(1)
        observer.assertValues(list)
    }
}