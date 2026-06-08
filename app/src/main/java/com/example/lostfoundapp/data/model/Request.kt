package com.example.lostfoundapp.data.model

data class Request(

    val id: Int,

    val requestType: RequestType,

    val itemName: String,

    val itemImageRes: Int? = null,

    val itemImageUrl: String? = null,

    val sender: User,

    val description: String,

    val additionalMessage: String,

    val status: RequestStatus,

    val createdAt: String
)