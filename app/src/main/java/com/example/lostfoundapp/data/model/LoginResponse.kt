package com.example.lostfoundapp.data.model

data class LoginResponse(
    val success: Boolean
)

data class PostsResponse(

    val data: List<PostResponse>
)

data class PostResponse(

    val id: Int,

    val user_id: Int,

    val title: String,

    val description: String,

    val category: CategoryResponse?,

    val location: LocationResponse?,

    val incident_date: String,

    val type: String,

    val picture: PictureResponse?
)

data class CategoryResponse(

    val id: Int,

    val name: String
)

data class LocationResponse(

    val id: Int,

    val name: String
)

data class PictureResponse(

    val url: String
)

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
    val user_name: String,
    val time: String
)