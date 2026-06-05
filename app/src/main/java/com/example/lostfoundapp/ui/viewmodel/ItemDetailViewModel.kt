package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ItemDetailViewModel : ViewModel() {

    var isSaved by mutableStateOf(false)
        private set

    fun toggleSaved() {
        isSaved = !isSaved
    }
}