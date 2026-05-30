package com.example.lostfoundapp.data.model

data class Request(

    val id: Int,

    val requestType: RequestType,

    val itemName: String,

    val senderName: String,

    val description: String,

    val additionalMessage: String,

    val contactInfo: String,

    val status: RequestStatus,

    val createdAt: String
)