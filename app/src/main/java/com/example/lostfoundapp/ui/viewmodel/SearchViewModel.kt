package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var selectedCategory by mutableStateOf("")
        private set

    var selectedLocation by mutableStateOf("")
        private set

    var selectedCategoryId by mutableStateOf<Int?>(null)
        private set

    var selectedLocationId by mutableStateOf<Int?>(null)
        private set

    var selectedDateFilter by mutableStateOf("Hoy")
        private set

    var showCategoryDialog by mutableStateOf(false)
        private set

    var showLocationDialog by mutableStateOf(false)
        private set

    fun updateSearchQuery(value: String) {
        searchQuery = value
    }

    fun updateCategory(
        id: Int?,
        name: String
    ) {
        selectedCategoryId = id
        selectedCategory = name
    }

    fun updateLocation(
        id: Int?,
        name: String
    ) {
        selectedLocationId = id
        selectedLocation = name
    }

    fun updateDateFilter(value: String) {
        selectedDateFilter = value
    }

    fun showCategoryDialog() {
        showCategoryDialog = true
    }

    fun hideCategoryDialog() {
        showCategoryDialog = false
    }

    fun showLocationDialog() {
        showLocationDialog = true
    }

    fun hideLocationDialog() {
        showLocationDialog = false
    }
}