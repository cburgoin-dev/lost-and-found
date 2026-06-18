package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.NotificationType

val mockNotifications = listOf(

    Notification(
        id = 1,
        type = NotificationType.REQUEST_APPROVED,
        title = "Solicitud aprobada",
        description = "Ya puedes contactar al propietario para recuperar la credencial UABCS.",
        time = "Hace 20 min",
        isRead = 0,
        postId = 2
    ),

    Notification(
        id = 2,
        type = NotificationType.MATCH_FOUND,
        title = "Posible coincidencia",
        description = "Encontramos una publicación que podría corresponder a tu mochila negra.",
        time = "Hace 1 h",
        isRead = 0,
        postId = 3
    ),

    Notification(
        id = 3,
        type = NotificationType.SYSTEM,
        title = "Consejo de seguridad",
        description = "Recuerda verificar la identidad antes de entregar o reclamar un objeto.",
        time = "Hace 3 h",
        isRead = 1,
        postId = null
    ),

    Notification(
        id = 4,
        type = NotificationType.REQUEST_REJECTED,
        title = "Solicitud rechazada",
        description = "Tu solicitud para reclamar la botella térmica fue rechazada.",
        time = "Hace 1 día",
        isRead = 1,
        postId = null
    ),

    Notification(
        id = 5,
        type = NotificationType.MATCH_FOUND,
        title = "Nueva coincidencia encontrada",
        description = "Revisa si los lentes encontrados coinciden con tu publicación.",
        time = "Hace 2 días",
        isRead = 1,
        postId = 6
    )
)