package com.example.lostfoundapp.data.model

data class ItemPost(

    val id: Int,

    val title: String,

    val description: String,

    val location: String,

    val date: String,

    val reportType: ReportType,

    val imageRes: Int? = null,

    val imageUrl: String? = null,

    val reporterName: String,

    val reporterImageRes: Int? = null,

    val isAnonymous: Boolean,

    val isContactVisible: Boolean
)