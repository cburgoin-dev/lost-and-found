package com.example.lostfoundapp.data.mapper

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.data.model.RequestType
import com.example.lostfoundapp.data.model.User
import com.example.lostfoundapp.data.response.RequestDto

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
            user?.toUser()
                ?: User(
                    id = -1,
                    name = "Usuario pendiente"
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