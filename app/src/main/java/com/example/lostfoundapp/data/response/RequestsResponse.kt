package com.example.lostfoundapp.data.response

import com.example.lostfoundapp.data.model.User

data class CreateRequestResponse(

    val message: String,

    val data: RequestData
)

data class RequestData(

    val id: Int,

    val type: String,

    val status: String,

    val post_title: String,

    val content: String,

    val message: String,

    val user: User?,

    val time: String
)

data class GetRequestsResponse(

    val data: List<RequestDto>
)

data class RequestDto(

    val id: Int,

    val type: String,

    val status: String,

    val title: String,

    val user: User?,

    val time: String,

    val content: String,

    val message: String
)