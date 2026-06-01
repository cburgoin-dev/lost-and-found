package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.data.model.RequestType

val mockRequests = listOf(

    Request(
        id = 1,
        requestType = RequestType.CLAIM,
        itemName = "Mochila Negra",
        itemImageRes = R.drawable.backpack,
        sender = carlosUser,
        description = "Mochila negra con llavero rojo",
        additionalMessage = "Soy estudiante de Ingeniería",
        status = RequestStatus.PENDING,
        createdAt = "Hace 10 min"
    ),

    Request(
        id = 2,
        requestType = RequestType.INFORMATION,
        itemName = "Credencial UABCS",
        itemImageRes = R.drawable.student_id,
        sender = anaUser,
        description = "La vi en la biblioteca ADSKMADKMAFKASFMKSAFMFAMASFKSFMAMAKSFMKAFSKMASFMKASFKMASF",
        additionalMessage = "",
        status = RequestStatus.PENDING,
        createdAt = "Hace 2 h"
    ),

    Request(
        id = 3,
        requestType = RequestType.CLAIM,
        itemName = "AirPods Case",
        itemImageRes = R.drawable.airpods_case,
        sender = sofiaUser,
        description = "Puedo identificar varios deetalles del estuche.",
        additionalMessage = "",
        status = RequestStatus.APPROVED,
        createdAt = "Hace 1 día"
    ),

    Request(
        id = 4,
        requestType = RequestType.CLAIM,
        itemName = "Botella Térmica",
        itemImageRes = R.drawable.water_bottle,
        sender = anaUser,
        description = "Creo que esta botella me pertenece.",
        additionalMessage = "",
        status = RequestStatus.REJECTED,
        createdAt = "Hace 3 días"
    ),

    Request(
        id = 5,
        requestType = RequestType.INFORMATION,
        itemName = "Mochila Negra",
        itemImageRes = R.drawable.backpack,
        sender = carlosUser,
        description = "La vi cerca del gimnasio.",
        additionalMessage = "Estaba junto a las gradas.",
        status = RequestStatus.PENDING,
        createdAt = "Hace 5 h"
    ),

    Request(
        id = 6,
        requestType = RequestType.CLAIM,
        itemName = "Laptop Lenovo ThinkPad T14 Gen 5 Color Negro",
        itemImageRes = R.drawable.airpods_case,
        sender = sofiaUser,
        description =
            "Puedo describir la etiqueta del equipo y el fondo de pantalla.",
        additionalMessage =
            "También puedo proporcionar el número de serie parcial.",
        status = RequestStatus.PENDING,
        createdAt = "Hace 30 min"
    )
)