package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType

val mockPosts = listOf(

    ItemPost(
        id = 1,

        title = "AirPods Case",

        description = "Estuche blanco encontrado...",

        location = "Biblioteca Central",

        date = "20 de mayo, 2026",

        reportType = ReportType.LOST,

        imageRes = R.drawable.airpods_case,

        reporterName = "Ana Sofía Perez",

        reporterImageRes = R.drawable.profile_placeholder,

        isAnonymous = false,

        isContactVisible = true
    ),

    ItemPost(
        id = 2,

        title = "Credencial UABCS",

        description = "Credencial encontrada cerca del Edificio A.",

        location = "Edificio A",

        date = "21 de mayo, 2026",

        reportType = ReportType.FOUND,

        imageRes = R.drawable.student_id,

        reporterName = "Carlos Mendoza",

        reporterImageRes = R.drawable.profile_placeholder,

        isAnonymous = false,

        isContactVisible = true
    ),

    ItemPost(
        id = 3,

        title = "Mochila Negra",

        description = "Mochila olvidada en el gimnasio.",

        location = "Gimnasio",

        date = "22 de mayo, 2026",

        reportType = ReportType.LOST,

        imageRes = R.drawable.backpack,

        reporterName = "Usuario anónimo",

        reporterImageRes = null,

        isAnonymous = true,

        isContactVisible = false
    ),

    ItemPost(
        id = 4,

        title = "Botella Térmica",

        description = "Botella térmica encontrada en la cafetería principal.",

        location = "Cafetería",

        date = "23 de mayo, 2026",

        reportType = ReportType.FOUND,

        imageRes = R.drawable.water_bottle,

        reporterName = "María Fernanda López",

        reporterImageRes = R.drawable.profile_placeholder,

        isAnonymous = false,

        isContactVisible = true
    )
)