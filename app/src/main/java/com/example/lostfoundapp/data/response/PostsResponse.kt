package com.example.lostfoundapp.data.response

data class PostsResponse(

    val data: List<PostResponse>
)

data class PostResponse(

    val id: Int,

    val hidden_user: Boolean,

    val user: PostUserResponse?,

    val title: String,

    val description: String,

    val category: CategoryResponse?,

    val location: LocationResponse?,

    val incident_date: String,

    val type: String,

    val picture: PictureResponse?,

    val yours: Boolean
)

data class PostUserResponse(

    val id: Int,

    val name: String,

    val email: String?,

    val phone: String?,

    val picture: String?
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