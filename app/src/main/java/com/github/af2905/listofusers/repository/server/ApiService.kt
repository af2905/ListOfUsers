package com.github.af2905.listofusers.repository.server

import com.github.af2905.listofusers.repository.database.pojo.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    //https://reqres.in/api/users?page=2
    @GET("users?page=2")
    fun getUsers(): Single<Response<UserResponse>>
}