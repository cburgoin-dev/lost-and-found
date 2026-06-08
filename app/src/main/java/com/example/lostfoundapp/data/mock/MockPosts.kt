package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostType

val mockPosts = listOf(

    ItemPost(
        id = 1,
        title = "AirPods Case",
        description = "Estuche blanco encontrado...",
        location = "Biblioteca Central",
        category = "Audífonos",
        date = "20 de mayo, 2026",
        postType = PostType.LOST,
        imageRes = R.drawable.airpods_case,
        publisherName = "anaUser",
        publisherEmail = null,
        publisherPhone = null,
        isAnonymous = false,
        isContactVisible = true,
        isMine = false,
        isBookmarked = false,
    ),

    ItemPost(
        id = 2,
        title = "Credencial UABCS",
        description = "Credencial encontrada cerca del Edificio A.",
        location = "Edificio A",
        category = "Documentos",
        date = "21 de mayo, 2026",
        postType = PostType.FOUND,
        imageRes = R.drawable.student_id,
        publisherName = "carlosUser",
        publisherEmail = null,
        publisherPhone = null,
        isAnonymous = false,
        isContactVisible = true,
        isMine = false,
        isBookmarked = false,
    ),

    ItemPost(
        id = 3,
        title = "Mochila Negra ASDKMASDMADSKDSMASAFMASFMASKFMSAFKMSAFMKASFMKSAFKFMK",
        description = "Mochila olvidada en el gimnasio.",
        location = "Gimnasio",
        date = "22 de mayo, 2026",
        category = "Mochilas",
        postType = PostType.LOST,
        imageRes = R.drawable.backpack,
        publisherName = "anonymousUser",
        publisherEmail = null,
        publisherPhone = null,
        isAnonymous = true,
        isContactVisible = false,
        isMine = false,
        isBookmarked = false,
    ),

    ItemPost(
        id = 4,
        title = "Botella Térmica",
        description = "Botella térmica encontrada en la cafetería principal.",
        location = "Cafetería",
        date = "23 de mayo, 2026",
        category = "Botellas",
        postType = PostType.FOUND,
        imageRes = R.drawable.water_bottle,
        publisherName = "sofiaUser",
        publisherEmail = null,
        publisherPhone = null,
        isAnonymous = false,
        isContactVisible = true,
        isMine = false,
        isBookmarked = false,
    )
)