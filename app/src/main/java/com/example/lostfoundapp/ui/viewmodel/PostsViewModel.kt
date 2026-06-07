package com.example.lostfoundapp.ui.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.Category
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.model.Location
import com.example.lostfoundapp.data.repository.CatalogRepository
import com.example.lostfoundapp.data.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel(
    sessionManager: SessionManager
) : ViewModel() {

    private val repository =
        PostsRepository(
            sessionManager
        )

    private val catalogRepository =
        CatalogRepository(sessionManager)
    var posts by mutableStateOf<List<ItemPost>>(emptyList())
        private set

    var categories by mutableStateOf<List<Category>>(emptyList())
        private set

    var locations by mutableStateOf<List<Location>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadPosts() {

        println("LOAD POSTS CALLED")

        viewModelScope.launch {

            isLoading = true

            repository
                .getPosts()
                .onSuccess {

                    posts = it
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun loadPosts(
        categoryId: Int?,
        locationId: Int?,
        time: String?
    ) {

        viewModelScope.launch {

            isLoading = true

            repository
                .getPosts(
                    categoryId = categoryId,
                    locationId = locationId,
                    time = time
                )
                .onSuccess {

                    posts = it
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }
    fun createPost(
        context: Context,
        reportType: ReportType,
        objectName: String,
        description: String,
        locationId: Int,
        categoryId: Int,
        date: String,
        publicContact: Boolean,
        selectedImageUri: Uri?,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            isLoading = true

            repository
                .createPost(
                    context = context,
                    reportType = reportType,
                    objectName = objectName,
                    description = description,
                    locationId = locationId,
                    categoryId = categoryId,
                    date = date,
                    publicContact = publicContact,
                    selectedImageUri = selectedImageUri
                )
                .onSuccess {

                    onSuccess()
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun loadCatalogs() {

        viewModelScope.launch {

            catalogRepository
                .getCategories()
                .onSuccess {

                    categories = it
                }

            catalogRepository
                .getLocations()
                .onSuccess {

                    locations = it
                }
        }
    }
}