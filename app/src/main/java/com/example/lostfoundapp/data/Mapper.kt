package com.example.lostfoundapp.data

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.data.model.RequestType
import com.example.lostfoundapp.data.model.User
import com.example.lostfoundapp.data.response.PostResponse
import com.example.lostfoundapp.data.response.RequestDto

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

fun RequestDto.toRequest(): Request {

    return Request(

        id = id,

        requestType =
            if(type.contains("Información"))
                RequestType.INFORMATION
            else
                RequestType.CLAIM,

        itemName = title,

        itemImageRes =
            R.drawable.airpods_case,

        sender =
            User(
                id = user?.id ?: -1,
                name = user?.name ?: "Usuario pendiente",
                email = user?.email ?: "-",
                phone = user?.phone ?: "-",
                profileImageRes = R.drawable.airpods_case
            ),

        description = content,

        additionalMessage = message,

        status =
            when(status) {

                "Aprobada" ->
                    RequestStatus.APPROVED

                "Rechazada" ->
                    RequestStatus.REJECTED

                else ->
                    RequestStatus.PENDING
            },

        createdAt = time
    )
}