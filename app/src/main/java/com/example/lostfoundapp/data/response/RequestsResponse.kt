package com.example.lostfoundapp.data.response

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

    val user: RequestUserDto?,

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

    val post: PostResponse?,

    val user: RequestUserDto?,

    val time: String,

    val content: String,

    val message: String
)

data class RequestUserDto(

    val id: Int,

    val name: String,

    val email: String?,

    val phone: String?
)