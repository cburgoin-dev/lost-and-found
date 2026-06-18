package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.mapper.toNotification
import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.remote.RetrofitInstance

import com.example.lostfoundapp.data.config.AppConfig
import com.example.lostfoundapp.data.mock.MockNotificationStore
import com.example.lostfoundapp.data.mock.mockNotifications

class NotificationsRepository (
    sessionManager: SessionManager
) {

    private val api = RetrofitInstance.createApi(sessionManager)

    suspend fun getNotifications(): Result<List<Notification>> {

        if (AppConfig.USE_MOCK_DATA) {
            return Result.success(
                MockNotificationStore.notifications
            )
        }

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

        if (AppConfig.USE_MOCK_DATA) {

            val index =
                MockNotificationStore.notifications.indexOfFirst {
                    it.id == notificationId
                }

            if (index != -1) {

                MockNotificationStore.notifications[index] =
                    MockNotificationStore.notifications[index].copy(
                        isRead = 1
                    )
            }

            return Result.success(Unit)
        }

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