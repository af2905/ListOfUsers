package com.github.af2905.listofusers.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.github.af2905.listofusers.repository.AppRepository
import com.github.af2905.listofusers.repository.database.entity.UserEntity
import com.github.af2905.listofusers.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AllUsersViewModel(application: Application, private val repository: AppRepository) :
    AndroidViewModel(application) {

    private val liveDataAllUsers = SingleLiveEvent<List<UserEntity>>()
    private val disposeBag = CompositeDisposable()

    fun loadAllUsersFromNetwork() {
        disposeBag.add(
            repository.getAllUsersFromNetwork()
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

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    companion object {
        private const val TAG = "TEST_OF_LOADING_DATA"
    }
}