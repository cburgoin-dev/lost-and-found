package com.example.lostfoundapp.ui.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EditProfileViewModel : ViewModel() {

    var phone by mutableStateOf("")
        private set

    var profileImageUri by mutableStateOf<Uri?>(null)
        private set

    fun startEditing(
        currentPhone: String,
        currentImage: Uri?
    ) {
        phone = currentPhone
        profileImageUri = currentImage
    }

    fun updatePhone(value: String) {
        phone = value
    }

    fun updateProfileImage(uri: Uri) {
        profileImageUri = uri
    }
}