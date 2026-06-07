package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.NotificationType

val mockNotifications = listOf(

    Notification(
        id = 1,
        type = NotificationType.REQUEST_APPROVED,
        title = "Solicitud aprobada",
        description = "Ya puedes ver la información de contacto del propietario.",
        time = "Hace 2 h",
        isRead = 0,
        postId = 3
    ),

    Notification(
        id = 2,
        type = NotificationType.MATCH_FOUND,
        title = "Posible coincidencia",
        description =
            "Encontramos una publicación que podría coincidir con tu objeto perdido.",
        time = "Hace 10 min",
        isRead = 0,
        postId = 3
    ),


    Notification(
        id = 3,
        type = NotificationType.SYSTEM,
        title = "Sistema",
        description =
        "Encontramos una publicación que podría coincidir con tu objeto perdido.",
        time = "Hace 10 min",
        isRead = 0,
        postId = null
),

    Notification(
        id = 4,
        type = NotificationType.REQUEST_REJECTED,
        title = "Solicitud rechazada",
        description = "xd",
        time = "Hace 10 min",
        isRead = 0,
        postId = null
    )
)