package com.example.lostfoundapp.data

import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.model.PostResponse

fun PostResponse.toItemPost(): ItemPost {

    return ItemPost(

        id = id,

        title = title,

        description = description,

        location = location?.name ?: "Sin ubicación",

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

        //imageUrl = picture?.url,

        reporterName = "Usuario",

        reporterImageRes = null,

        isAnonymous = false,

        isContactVisible = true

    )

}