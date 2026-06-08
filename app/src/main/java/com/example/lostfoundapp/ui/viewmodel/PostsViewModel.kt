package com.example.lostfoundapp.ui.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lostfoundapp.data.model.Category
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.data.model.Location
import com.example.lostfoundapp.data.repository.CatalogRepository
import com.example.lostfoundapp.data.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel(
    private val postsRepository: PostsRepository,
    private val catalogRepository: CatalogRepository
) : ViewModel() {

    var posts by mutableStateOf<List<ItemPost>>(emptyList())
        private set

    var myPosts by mutableStateOf<List<ItemPost>>(emptyList())
        private set

    var bookmarkedPosts by mutableStateOf<List<ItemPost>>(emptyList())
        private set

    var categories by mutableStateOf<List<Category>>(emptyList())
        private set

    var locations by mutableStateOf<List<Location>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var showPostCreatedMessage by mutableStateOf(false)
        private set

    var isReported by mutableStateOf(false)
        private set

    fun clearPostCreatedMessage() {
        showPostCreatedMessage = false
    }

    fun findPostById(
        postId: Int
    ): ItemPost? {

        return posts.find {
            it.id == postId
        }
            ?: myPosts.find {
                it.id == postId
            }
            ?: bookmarkedPosts.find {
                it.id == postId
            }
    }

    fun loadPosts() {

        viewModelScope.launch {

            isLoading = true

            postsRepository
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

            postsRepository
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

    fun loadMyPosts() {

        viewModelScope.launch {

            isLoading = true

            postsRepository
                .getMyPosts()
                .onSuccess {

                    myPosts = it
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun createPost(
        context: Context,
        postType: PostType,
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

            postsRepository
                .createPost(
                    context = context,
                    postType = postType,
                    objectName = objectName,
                    description = description,
                    locationId = locationId,
                    categoryId = categoryId,
                    date = date,
                    publicContact = publicContact,
                    selectedImageUri = selectedImageUri
                )
                .onSuccess {
                    showPostCreatedMessage = true
                    onSuccess()
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun loadBookmarks() {

        viewModelScope.launch {

            isLoading = true

            postsRepository
                .getBookmarks()
                .onSuccess {

                    bookmarkedPosts = it
                }
                .onFailure {

                    errorMessage = it.message
                }

            isLoading = false
        }
    }

    fun toggleBookmark(
        postId: Int
    ) {

        viewModelScope.launch {

            postsRepository
                .toggleBookmark(postId)
                .onSuccess {

                    posts =
                        updateBookmark(posts, postId)

                    myPosts =
                        updateBookmark(myPosts, postId)

                    bookmarkedPosts =
                        updateBookmark(bookmarkedPosts, postId)

                    loadBookmarks()
                }
                .onFailure {

                    errorMessage = it.message
                }
        }
    }

    private fun updateBookmark(
        list: List<ItemPost>,
        postId: Int
    ): List<ItemPost> {

        return list.map {

            if(it.id == postId) {

                it.copy(
                    isBookmarked = !it.isBookmarked
                )

            } else {

                it
            }
        }
    }

    fun reportPost(
        postId: Int,
        reason: String
    ) {

        viewModelScope.launch {

            postsRepository
                .reportPost(
                    postId = postId,
                    reason = reason
                )
                .onSuccess {

                    isReported = true
                }
                .onFailure {

                    errorMessage = it.message
                }
        }
    }

    fun completePost(
        postId: Int,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            postsRepository
                .completePost(postId)
                .onSuccess {

                    onSuccess()

                    loadPosts()
                    loadMyPosts()
                    loadBookmarks()
                }
                .onFailure {

                    errorMessage = it.message
                }
        }
    }
}