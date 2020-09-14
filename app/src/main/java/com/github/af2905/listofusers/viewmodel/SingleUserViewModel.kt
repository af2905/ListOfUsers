package com.github.af2905.listofusers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.github.af2905.listofusers.repository.AppRepository
import com.github.af2905.listofusers.repository.database.entity.UserEntity
import com.github.af2905.listofusers.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SingleUserViewModel(application: Application, private val repository: AppRepository) :
    AndroidViewModel(application) {
    private val liveDataUser = SingleLiveEvent<UserEntity>()
    private val disposeBag = CompositeDisposable()

    fun getUser(id: Int) {
        disposeBag.add(
            repository.getSingleUserFromDatabase(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ liveDataUser.value = it }, { })
        )
    }

    fun updateUser(userEntity: UserEntity) {
        repository.updateSingleUserInDatabase(userEntity)
    }

    fun getLiveDataUser(): LiveData<UserEntity> {
        return liveDataUser
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}