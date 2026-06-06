package com.example.lostfoundapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.data.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel(
    sessionManager: SessionManager
) : ViewModel() {

    private val repository =
        PostsRepository(
            RetrofitInstance.api,
            sessionManager
        )

    var posts by mutableStateOf<List<ItemPost>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadPosts() {

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
}