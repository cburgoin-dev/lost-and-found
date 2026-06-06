package com.example.lostfoundapp.data.mock

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.NotificationType

val mockNotifications = listOf(

    Notification(
        id = 1,
        type = "Solicitud aprobada",
        description = "Ya puedes ver la información de contacto del propietario.",
        time = "Hace 2 h",
        is_read = 0,
        post_id = 3
    ),

    Notification(
        id = 2,
        type = "Posible coincidencia",
        description =
            "Encontramos una publicación que podría coincidir con tu objeto perdido.",
        time = "Hace 10 min",
        is_read = 0,
        post_id = 3
    ),


            Notification(
            id = 3,
    type = "Sistema",
    description =
        "Encontramos una publicación que podría coincidir con tu objeto perdido.",
    time = "Hace 10 min",
    is_read = 0,
    post_id = null
),

Notification(
id = 4,
type = "Solicitud rechazada",
description =
"xd",
time = "Hace 10 min",
is_read = 0,
post_id = null
)
)