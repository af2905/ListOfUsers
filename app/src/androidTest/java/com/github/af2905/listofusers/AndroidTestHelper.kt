package com.github.af2905.listofusers

import com.github.af2905.listofusers.repository.database.entity.UserEntity

class AndroidTestHelper {
    val users = listOf(
        UserEntity(1, "email@yandex.ru", "John", "Brown"),
        UserEntity(2, "email@gmail.com", "Mike", "Black"),
        UserEntity(3, "email@mail.ru", "Tom", "White")
    )
}