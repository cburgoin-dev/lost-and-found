package com.example.lostfoundapp.data.model

data class User(

    val id: Int,

    val fullName: String,

    val profileImageRes: Int? = null,

    val email: String? = null,

    val phone: String? = null,
)