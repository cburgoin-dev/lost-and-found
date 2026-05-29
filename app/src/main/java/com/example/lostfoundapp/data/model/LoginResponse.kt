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