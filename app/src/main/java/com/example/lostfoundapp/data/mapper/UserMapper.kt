package com.example.lostfoundapp.data.mapper

import com.example.lostfoundapp.data.model.User
import com.example.lostfoundapp.data.response.RequestUserDto
import com.example.lostfoundapp.data.response.UserDto

fun UserDto.toUser(): User {

    return User(

        id = id,

        name = name,

        email = email,

        phone = phone
    )
}

fun RequestUserDto.toUser(): User {

    return User(
        id = id,
        name = name,
        email = email,
        phone = phone
    )
}