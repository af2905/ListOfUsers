package com.github.af2905.listofusers.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.af2905.listofusers.repository.AppRepository
import com.github.af2905.listofusers.repository.database.entity.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AllUsersViewModel(application: Application, private val repository: AppRepository) :
    AndroidViewModel(application) {
    private val liveDataAllUsers = MutableLiveData<List<UserEntity>>()
    private val disposeBag = CompositeDisposable()

    init {
        loadAllUsersFromNetwork()
    }

    private fun loadAllUsersFromNetwork() {
        disposeBag.add(
            repository.getAllUsersFromNetwork()
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG + it.size, it.toString())
                    liveDataAllUsers.value = it
                }, { Log.d(TAG, it.message.toString()) })
        )
    }

    fun loadAllUsersFromDatabase() {
        disposeBag.add(
            repository.getAllUsersFromDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG + it.size, it.toString())
                    liveDataAllUsers.value = it
                }, { Log.d(TAG, it.message.toString()) })
        )
    }

    fun deleteUserFromDatabase(userEntity: UserEntity) {
        repository.deleteSingleUserFromDatabase(userEntity)
    }

    fun updateDatabase() {
        disposeBag.add(
            repository.updateAllUsersInDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG + it.size, it.toString())
                    liveDataAllUsers.value = it
                }, { Log.d(TAG, it.message.toString()) })
        )
    }

    fun getLiveDataAllUsers(): LiveData<List<UserEntity>> {
        return liveDataAllUsers
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    companion object {
        private const val TAG = "TEST_OF_LOADING_DATA"
    }
}