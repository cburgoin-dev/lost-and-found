package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    sessionManager: SessionManager
) : ViewModel() {

    private val authRepository =
        AuthRepository(
            sessionManager
        )

    var isLoading by mutableStateOf(false)
        private set

    var authSuccess by mutableStateOf(false)
        private set

    var forgotPasswordSuccess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var logoutSuccess by mutableStateOf(false)
        private set

    fun login(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            isLoading = true

            val result =
                authRepository.login(
                    email,
                    password
                )

            isLoading = false

            result
                .onSuccess {

                    authSuccess = true
                }
                .onFailure {

                    errorMessage = it.message
                }
        }
    }

    fun logout(){

        viewModelScope.launch {

            isLoading = true

            authRepository
                .logout()
                .onSuccess {

                    logoutSuccess = true
                }
                .onFailure {

                    logoutSuccess = false

                    errorMessage = it.message
                }

            isLoading = false
        }

    }

    fun signup(
        name: String,
        email: String,
        password: String,
        phone: String
    ) {

        viewModelScope.launch {

            isLoading = true

            val result =
                authRepository.signup(
                    name,
                    email,
                    password,
                    phone
                )

            isLoading = false

            result
                .onSuccess {

                    authSuccess = true
                }
                .onFailure {

                    errorMessage = it.message
                }
        }
    }

    fun forgotPassword(
        email: String
    ) {

        viewModelScope.launch {

            isLoading = true

            val result =
                authRepository.forgotPassword(
                    email
                )

            isLoading = false

            result
                .onSuccess {

                    forgotPasswordSuccess = true
                }
                .onFailure {

                    errorMessage = it.message
                }
        }
    }
}