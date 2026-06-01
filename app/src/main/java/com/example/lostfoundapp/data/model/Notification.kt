package com.example.lostfoundapp.data.model

data class Notification(

    val id: Int,

    val type: NotificationType,

    val title: String,

    val description: String,

    val createdAt: String,

    val isRead: Boolean,

    val relatedItemId: Int? = null
)