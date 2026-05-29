package com.example.lostfoundapp.data.model

data class ItemPost(

    val id: Int,

    val title: String,

    val description: String,

    val location: String,

    val category: String,

    val date: String,

    val reportType: ReportType,

    val imageRes: Int? = null,

    val reporter: User,

    val isAnonymous: Boolean,

    val isContactVisible: Boolean
)