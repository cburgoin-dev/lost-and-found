package com.example.lostfoundapp.data.response

data class NotificationResponse(

    val id: Int,

    val type: String,

    val description: String,

    val time: String,

    val is_read: Int,

    val post_id: Int?
)