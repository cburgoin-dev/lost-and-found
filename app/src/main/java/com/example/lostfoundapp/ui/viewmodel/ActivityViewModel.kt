package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestStatus

class ActivityViewModel : ViewModel() {

    var selectedTab by mutableStateOf("Solicitudes")
        private set

    var hasUnreadNotifications by mutableStateOf(false)
        private set

    var hasPendingRequests by mutableStateOf(false)
        private set

    val hasUnreadActivity: Boolean
        get() = hasUnreadNotifications || hasPendingRequests

    fun updatePendingRequests(
        requests: List<Request>
    ) {
        hasPendingRequests =
            requests.any {
                it.status == RequestStatus.PENDING
            }
    }

    fun updateUnreadNotifications(
        notifications: List<Notification>
    ) {

        hasUnreadNotifications =
            notifications.any {
                it.isRead == 0
            }
    }

    fun selectTab(tab: String) {
        selectedTab = tab
    }
}