package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.repository.RequestsRepository

import kotlinx.coroutines.launch

class RequestsViewModel(
    private val repository: RequestsRepository
) : ViewModel() {

    var requests by mutableStateOf<List<Request>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadRequests() {

        viewModelScope.launch {

            isLoading = true

            repository
                .getRequests()
                .onSuccess {

                    requests = it
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun createRequest(
        postId: Int,
        content: String,
        message: String,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            isLoading = true

            repository
                .createRequest(
                    postId = postId,
                    content = content,
                    message = message,

                )
                .onSuccess {

                    onSuccess()
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun approveRequest(
        requestId: Int,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            isLoading = true

            repository
                .approveRequest(
                    requestId
                )
                .onSuccess {
                    loadRequests()
                    onSuccess()
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun declineRequest(
        requestId: Int,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            isLoading = true

            repository
                .declineRequest(
                    requestId
                )
                .onSuccess {
                    loadRequests()
                    onSuccess()
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }
}