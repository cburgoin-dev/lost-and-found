package com.example.lostfoundapp.data.mapper

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.NotificationType
import com.example.lostfoundapp.data.response.NotificationResponse

fun NotificationResponse.toNotification(): Notification {

    val notificationType =
        when(type) {

            "Solicitud aprobada" ->
                NotificationType.REQUEST_APPROVED

            "Solicitud rechazada" ->
                NotificationType.REQUEST_REJECTED

            "Posible coincidencia" ->
                NotificationType.MATCH_FOUND

            else ->
                NotificationType.SYSTEM
        }

    return Notification(

        id = id,

        type = notificationType,

        title = type,

        description = description,

        time = time,

        isRead = is_read,

        postId = post_id
    )
}