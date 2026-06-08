package com.example.lostfoundapp.ui.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditProfileViewModel : ViewModel() {

    var name by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var profileImageUri by mutableStateOf<Uri?>(null)
        private set

    fun startEditing(
        currentName: String,
        currentPhone: String,
        currentImage: Uri?
    ) {
        name = currentName
        phone = currentPhone
        profileImageUri = currentImage
    }

    fun updateName(value: String) {
        name = value
    }

    fun updatePhone(value: String) {
        phone = value
    }

    fun updateProfileImage(uri: Uri) {
        profileImageUri = uri
    }
}