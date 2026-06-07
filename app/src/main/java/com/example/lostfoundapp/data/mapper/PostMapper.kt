package com.example.lostfoundapp.data.mapper

import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.response.PostResponse

fun PostResponse.toItemPost(): ItemPost {

    return ItemPost(

        id = id,

        title = title,

        description = description,

        location = location?.name ?: "Sin ubicación",

        category = category?.name ?: "Sin nombre",

        date = incident_date,

        reportType =
            if(type == "Perdido")
                ReportType.LOST
            else
                ReportType.FOUND,

        imageRes = null,

        imageUrl =
            when {

                picture?.url == null -> null

                picture.url.startsWith("http://localhost") -> {

                    picture.url.replace(
                        "http://localhost:8000",
                        "http://10.0.2.2:8000"
                    )
                }

                picture.url.startsWith("https://") -> {

                    picture.url
                }

                else -> {

                    "http://10.0.2.2:8000/storage/pictures/${picture.url}"
                }
            },

        reporterName =
            if(hidden_user)
                "Usuario anónimo"
            else
                user?.name ?: "Usuario",

        reporterEmail =
            user?.email,

        reporterPhone =
            user?.phone,

        isAnonymous =
            hidden_user,

        isContactVisible =
            !hidden_user,

        isMine =
            yours
    )
}