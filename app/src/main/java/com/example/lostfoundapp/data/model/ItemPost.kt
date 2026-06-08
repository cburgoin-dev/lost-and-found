package com.example.lostfoundapp.data.model

data class ItemPost(

    val id: Int,

    val title: String,

    val description: String,

    val location: String,

    val category: String,

    val date: String,

    val postType: PostType,

    val imageRes: Int? = null,

    val imageUrl: String? = null,

    val publisherName: String,

    val publisherEmail: String?,

    val publisherPhone: String?,

    val publisherImageRes: Int? = null,

    val publisherImageUrl: String? = null,

    val isAnonymous: Boolean,

    val isContactVisible: Boolean,

    val isMine: Boolean
)