package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.NotificationType

val mockNotifications = listOf(

    Notification(
        id = 1,
        type = NotificationType.REQUEST_APPROVED,
        title = "Solicitud aprobada",
        description = "Ya puedes ver la información de contacto del propietario.",
        createdAt = "Hace 2 h",
        isRead = true,
        relatedItemId = 3
    ),

    Notification(
        id = 2,
        type = NotificationType.MATCH_FOUND,
        title = "Posible coincidencia encontrada",
        description =
            "Encontramos una publicación que podría coincidir con tu objeto perdido.",
        createdAt = "Hace 3 días",
        isRead = false,
        relatedItemId = 2
    )
)