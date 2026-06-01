package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {

    var selectedTab by mutableStateOf(
        "Solicitudes"
    )
        private set

    fun selectTab(
        tab: String
    ) {
        selectedTab = tab
    }
}