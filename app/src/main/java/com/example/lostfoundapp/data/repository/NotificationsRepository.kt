package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.mapper.toNotification
import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.remote.RetrofitInstance

class NotificationsRepository (
    sessionManager: SessionManager
) {

    private val api = RetrofitInstance.createApi(sessionManager)

    suspend fun getNotifications():
        Result<List<Notification>> {

        return try {

            val response = api.getNotifications()

            val notifications =
                response.data
                    ?.map {
                        it.toNotification()
                    }
                    ?: emptyList()

            Result.success(
                notifications
            )

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun markNotificationAsRead(
        notificationId: Int
    ): Result<Unit> {

        return try {

            val response =
                api.markNotificationAsRead(
                    notificationId
                )

            if (response.isSuccessful) {

                Result.success(Unit)

            } else {

                Result.failure(
                    Exception(
                        "Error marking notification as read"
                    )
                )
            }
        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}