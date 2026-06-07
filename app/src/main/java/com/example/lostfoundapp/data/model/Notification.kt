package com.example.lostfoundapp.data.model

data class Notification(

    val id: Int,

    val type: NotificationType,

    val title: String,

    val description: String,

    val time: String,

    val isRead: Int,

    val postId: Int? = null
)