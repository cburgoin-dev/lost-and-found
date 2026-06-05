package com.example.lostfoundapp.ui.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    var userName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var profileImageUri by mutableStateOf<Uri?>(null)
        private set

    init {
        loadUser()
    }

    fun loadUser() {
        // LOAD USER FROM BACKEND
    }

    fun updateUser(
        phone: String,
        imageUri: Uri?
    ) {
        this.phone = phone
        this.profileImageUri = imageUri

        // CALL UPDATE PROFILE ENDPOINT
    }

    fun logout() {
        // BACKEND
    }
}