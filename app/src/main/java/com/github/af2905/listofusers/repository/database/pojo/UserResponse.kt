package com.github.af2905.listofusers.repository.database.pojo

import com.github.af2905.listofusers.repository.database.entity.UserEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse (
    @SerializedName("data")
    @Expose
    val data: List<UserEntity>? = null
)