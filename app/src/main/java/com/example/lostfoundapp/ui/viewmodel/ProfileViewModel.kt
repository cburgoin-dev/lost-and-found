package com.example.lostfoundapp.ui.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    var userName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var profileImageUri by mutableStateOf<Uri?>(null)
        private set

    var editedPhone by mutableStateOf("")
        private set

    var editedProfileImageUri by mutableStateOf<Uri?>(null)
        private set

    init {
        loadProfile()
    }

    fun loadProfile() {

        // BACKEND

        userName = "Cristian Burgoin"
        email = "cristian@uabcs.mx"
        phone = "6121234567"
    }

    fun startEditing() {

        editedPhone = phone
        editedProfileImageUri = profileImageUri
    }

    fun updateEditedPhone(value: String) {
        editedPhone = value
    }

    fun updateEditedProfileImage(uri: Uri) {
        editedProfileImageUri = uri
    }

    fun saveProfile() {

        phone = editedPhone
        profileImageUri = editedProfileImageUri

        // BACKEND
    }

    fun logout() {

        // BACKEND
    }
}