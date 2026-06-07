package com.example.lostfoundapp.data.response

data class UserResponse(
    val data: UserDto
)

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val picture: String?
)