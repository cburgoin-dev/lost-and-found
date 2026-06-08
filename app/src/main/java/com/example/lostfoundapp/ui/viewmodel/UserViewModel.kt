package com.example.lostfoundapp.ui.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lostfoundapp.data.repository.UserRepository
import com.example.lostfoundapp.data.model.User
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var user by mutableStateOf<User?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var updateSuccess by mutableStateOf(false)
        private set

    init {
        loadUser()
    }

    fun loadUser() {

        viewModelScope.launch {

            isLoading = true

            repository
                .getUser()
                .onSuccess {

                    user = it
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun clearUpdateSuccess() {
        updateSuccess = false
    }

    fun updateUser(
        context: Context,
        name: String,
        phone: String,
        imageUri: Uri?
    ) {

        updateSuccess = false

        viewModelScope.launch {

            isLoading = true

            repository
                .updateUser(
                    context = context,
                    name = name,
                    phone = phone,
                    imageUri = imageUri
                )
                .onSuccess {

                    user = it

                    updateSuccess = true
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }
}