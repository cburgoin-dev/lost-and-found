package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.data.model.RequestType

val mockRequests = listOf(

    Request(
        id = 1,
        requestType = RequestType.CLAIM,
        itemName = "Credencial UABCS",
        itemImageRes = R.drawable.student_id,
        sender = anaUser,
        description = "La credencial está a nombre de Ana Sofía Pérez. Puedo proporcionar mi matrícula para verificar la propiedad.",
        additionalMessage = "Agradecería mucho recuperar este documento.",
        status = RequestStatus.PENDING,
        createdAt = "Hace 20 min"
    ),

    Request(
        id = 2,
        requestType = RequestType.INFORMATION,
        itemName = "Mochila negra Samsonite",
        itemImageRes = R.drawable.backpack,
        sender = carlosUser,
        description = "La vi cerca de las gradas del gimnasio después del entrenamiento.",
        additionalMessage = "Parecía que nadie la había recogido todavía.",
        status = RequestStatus.PENDING,
        createdAt = "Hace 2 h"
    ),

    Request(
        id = 3,
        requestType = RequestType.CLAIM,
        itemName = "Calculadora científica Casio fx-991ES Plus",
        itemImageRes = R.drawable.calculator_casio,
        sender = sofiaUser,
        description = "Puedo identificar una etiqueta con iniciales en la parte posterior.",
        additionalMessage = "Gracias por mantener activa esta publicación.",
        status = RequestStatus.APPROVED,
        createdAt = "Hace 1 día"
    ),

    Request(
        id = 4,
        requestType = RequestType.CLAIM,
        itemName = "Botella térmica gris",
        itemImageRes = R.drawable.water_bottle,
        sender = anaUser,
        description = "Pensé que era mi botella porque es muy parecida.",
        additionalMessage = "Después revisé y encontré la mía.",
        status = RequestStatus.REJECTED,
        createdAt = "Hace 2 días"
    ),

    Request(
        id = 5,
        requestType = RequestType.INFORMATION,
        itemName = "Memoria USB Kingston 64 GB",
        itemImageRes = R.drawable.usb,
        sender = carlosUser,
        description = "Pregunté en el laboratorio y comentaron que alguien la entregó al encargado.",
        additionalMessage = "Tal vez puedas verificar con el personal.",
        status = RequestStatus.PENDING,
        createdAt = "Hace 5 h"
    ),

    Request(
        id = 6,
        requestType = RequestType.CLAIM,
        itemName = "Lentes graduados en estuche negro",
        itemImageRes = R.drawable.graduated_lenses,
        sender = sofiaUser,
        description = "Puedo describir el color del estuche y la graduación aproximada.",
        additionalMessage = "Son indispensables para mis clases.",
        status = RequestStatus.PENDING,
        createdAt = "Hace 45 min"
    )
)