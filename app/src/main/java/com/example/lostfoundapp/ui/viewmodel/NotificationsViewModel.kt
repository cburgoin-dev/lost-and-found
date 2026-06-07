package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.repository.NotificationsRepository
import com.example.lostfoundapp.data.response.ApiResult
import kotlinx.coroutines.launch

class NotificationsViewModel (
    private val repository: NotificationsRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var notifications by mutableStateOf(emptyList<Notification>())

    var hasNotifications by mutableStateOf(false)

    fun getNotifications(){
        if(isLoading) return;

        viewModelScope.launch {
            isLoading = true

            when (val result = repository.getNotifications()) {

                is ApiResult.Success -> {
                    notifications = result.data ?: emptyList()
                    hasNotifications  = notifications.isNotEmpty()
                }

                is ApiResult.Error<*> -> {
                    hasNotifications = false
                    println(result.message)
                }
            }

            isLoading = false
        }
    }
}