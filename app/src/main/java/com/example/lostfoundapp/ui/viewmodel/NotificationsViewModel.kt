package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.repository.NotificationsRepository
import kotlinx.coroutines.launch

class NotificationsViewModel (
    private val repository: NotificationsRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var notifications by mutableStateOf(emptyList<Notification>())
        private set

    var hasNotifications by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadNotifications() {

        if(isLoading) return;

        viewModelScope.launch {

            isLoading = true

            repository
                .getNotifications()
                .onSuccess {

                    notifications = it

                    hasNotifications = it.isNotEmpty()
                }
                .onFailure {

                    hasNotifications = false

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun markNotificationAsRead(
        notificationId: Int
    ) {

        viewModelScope.launch {

            repository
                .markNotificationAsRead(
                    notificationId
                )
                .onSuccess {

                    notifications =
                        notifications.map {

                            if(it.id == notificationId) {

                                it.copy(
                                    isRead = 1
                                )

                            } else {

                                it
                            }
                        }
                }
                .onFailure {

                    errorMessage = it.message
                }
        }
    }

    fun markAllNotificationsAsRead() {

        viewModelScope.launch {

            notifications
                .filter { it.isRead == 0 }
                .forEach { notification ->

                    repository
                        .markNotificationAsRead(
                            notification.id
                        )
                        .onFailure {

                            errorMessage = it.message
                        }
                }

            notifications =
                notifications.map {
                    it.copy(isRead = 1)
                }
        }
    }
}