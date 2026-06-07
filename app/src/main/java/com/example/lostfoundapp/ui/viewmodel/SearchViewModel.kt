package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.repository.PostsRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    sessionManager: SessionManager
) : ViewModel() {

    private val repository =
        PostsRepository(sessionManager)

    var searchResults by mutableStateOf<List<ItemPost>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

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

    fun searchPosts(
        categoryId: Int?,
        locationId: Int?,
        time: String?
    ) {

        viewModelScope.launch {

            isLoading = true

            repository
                .getPosts(
                    categoryId,
                    locationId,
                    time
                )
                .onSuccess {

                    searchResults = it
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }
}