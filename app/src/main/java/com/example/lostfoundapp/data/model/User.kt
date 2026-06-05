package com.example.lostfoundapp.data.model

data class User(

    val id: Int,

    val name: String,

    val profileImageRes: Int? = null,

    val email: String? = null,

    val phone: String? = null,
)