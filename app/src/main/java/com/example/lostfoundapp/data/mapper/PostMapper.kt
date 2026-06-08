package com.example.lostfoundapp.data.mapper

import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.data.response.PostResponse

fun PostResponse.toItemPost(): ItemPost {

    return ItemPost(

        id = id,

        title = title,

        description = description,

        location = location?.name ?: "Sin ubicación",

        category = category?.name ?: "Sin nombre",

        date = incident_date,

        postType =
            if(type == "Perdido")
                PostType.LOST
            else
                PostType.FOUND,

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

        publisherName =
            if(hidden_user)
                "Usuario anónimo"
            else
                user?.name ?: "Usuario",

        publisherEmail =
            user?.email,

        publisherPhone =
            user?.phone,

        publisherImageUrl =
            user?.picture,

        isAnonymous =
            hidden_user,

        isContactVisible =
            !hidden_user,

        isMine =
            yours
    )
}