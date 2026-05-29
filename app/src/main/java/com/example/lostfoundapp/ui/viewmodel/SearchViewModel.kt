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

    var selectedDateFilter by mutableStateOf("Hoy")
        private set

    var showCategoryDialog by mutableStateOf(false)
        private set

    var showLocationDialog by mutableStateOf(false)
        private set

    fun updateSearchQuery(value: String) {
        searchQuery = value
    }

    fun updateCategory(value: String) {
        selectedCategory = value
    }

    fun updateLocation(value: String) {
        selectedLocation = value
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