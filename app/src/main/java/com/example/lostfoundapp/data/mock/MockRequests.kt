package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.data.model.RequestType

val mockRequests = listOf(

    Request(
        id = 1,

        requestType = RequestType.CLAIM,

        itemName = "Mochila Negra",

        senderName = "Carlos López",

        description = "Mochila negra con llavero rojo",

        additionalMessage = "Soy estudiante de Ingeniería",

        contactInfo = "carlos@email.com",

        status = RequestStatus.PENDING,

        createdAt = "Hace 10 min"
    ),

    Request(
        id = 2,

        requestType = RequestType.INFORMATION,

        itemName = "Credencial UABCS",

        senderName = "Ana Pérez",

        description = "La vi en la biblioteca ADSKMADKMAFKASFMKSAFMFAMASFKSFMAMAKSFMKAFSKMASFMKASFKMASF",

        additionalMessage = "",

        contactInfo = "ana@email.com",

        status = RequestStatus.PENDING,

        createdAt = "Hace 2 h"
    )
)