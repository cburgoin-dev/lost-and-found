package com.example.lostfoundapp.data.mapper

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.response.NotificationResponse

fun NotificationResponse.toNotification(): Notification {

    return Notification(

        id = id,

        type = type,

        description = description,

        time = time,

        is_read = is_read,

        post_id = post_id
    )
}