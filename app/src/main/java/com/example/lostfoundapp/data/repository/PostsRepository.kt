package com.example.lostfoundapp.data.repository

import android.content.Context
import android.net.Uri

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.data.mapper.toItemPost
import com.example.lostfoundapp.utils.toRequestBodyText
import com.example.lostfoundapp.utils.uriToMultipart

import com.example.lostfoundapp.data.config.AppConfig
import com.example.lostfoundapp.data.mock.MockStore

class PostsRepository(
    sessionManager: SessionManager
) {

    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun getPosts(): Result<List<ItemPost>> {

        if (AppConfig.USE_MOCK_DATA) {
            return Result.success(MockStore.posts)
        }

        return try {

            val response =
                api.getPosts()

            if(response.isSuccessful) {

                val posts =
                    response.body()
                        ?.data
                        ?.map { it.toItemPost() }
                        ?: emptyList()

                Result.success(posts)

            } else {

                Result.failure(
                    Exception("Error loading posts")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun getPosts(
        categoryId: Int?,
        locationId: Int?,
        time: String?
    ): Result<List<ItemPost>> {

        if (AppConfig.USE_MOCK_DATA) {

            var filtered = MockStore.posts.toList()

            categoryId?.let {
                filtered = filtered.filter {
                        post -> post.categoryId == it
                }
            }

            locationId?.let {
                filtered = filtered.filter {
                        post -> post.locationId == it
                }
            }

            time?.let { filter ->

                filtered = when (filter) {

                    "hoy" -> filtered.filter {

                        it.createdAt.contains("min") ||
                                it.createdAt.contains("hora")
                    }

                    "esta semana" -> filtered.filter {

                        it.createdAt.contains("min") ||
                                it.createdAt.contains("hora") ||
                                it.createdAt.contains("día")
                    }

                    "este mes" -> filtered.filter {

                        !it.createdAt.contains("mes")
                    }

                    "todo el tiempo" -> filtered

                    else -> filtered
                }
            }

            return Result.success(filtered)
        }

        return try {

            val response =
                api.getPosts(
                    categoryId = categoryId,
                    locationId = locationId,
                    time = time
                )

            if(response.isSuccessful) {

                val posts =
                    response.body()
                        ?.data
                        ?.map { it.toItemPost() }
                        ?: emptyList()

                Result.success(posts)

            } else {

                Result.failure(
                    Exception("Error loading posts")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun createPost(
        context: Context,
        postType: PostType,
        objectName: String,
        description: String,
        locationId: Int,
        categoryId: Int,
        date: String,
        publicContact: Boolean,
        selectedImageUri: Uri?
    ): Result<Unit> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.failure(
                Exception(
                    "Esta función requiere conexión con el servidor."
                )
            )
        }

        return try {

            val imagePart =
                selectedImageUri?.let {

                    uriToMultipart(
                        context = context,
                        uri = it
                    )
                }

            val response =
                api.createPost(

                    type =
                        if(postType == PostType.LOST)
                            "Perdido".toRequestBodyText()
                        else
                            "Encontrado".toRequestBodyText(),

                    title =
                        objectName.toRequestBodyText(),

                    description =
                        description.toRequestBodyText(),

                    locationId =
                        locationId.toString()
                            .toRequestBodyText(),

                    categoryId =
                        categoryId.toString()
                            .toRequestBodyText(),

                    incidentDate =
                        date.toRequestBodyText(),

                    shareContact =
                        publicContact.toString()
                            .toRequestBodyText(),

                    picture =
                        imagePart
                )

            if(response.isSuccessful) {

                Result.success(Unit)

            } else {

                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "Error creating post"
                    )
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun updatePost(
        postId: Int,
        context: Context,
        postType: PostType,
        objectName: String,
        description: String,
        locationId: Int,
        categoryId: Int,
        date: String,
        publicContact: Boolean,
        selectedImageUri: Uri?
    ): Result<ItemPost> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.failure(
                Exception(
                    "Esta función requiere conexión con el servidor."
                )
            )
        }

        return try {

            val imagePart =
                selectedImageUri?.let {

                    uriToMultipart(
                        context = context,
                        uri = it
                    )
                }

            val response =
                api.updatePost(

                    postId = postId,

                    type =
                        if(postType == PostType.LOST)
                            "Perdido".toRequestBodyText()
                        else
                            "Encontrado".toRequestBodyText(),

                    title =
                        objectName.toRequestBodyText(),

                    description =
                        description.toRequestBodyText(),

                    locationId =
                        locationId.toString()
                            .toRequestBodyText(),

                    categoryId =
                        categoryId.toString()
                            .toRequestBodyText(),

                    incidentDate =
                        date.toRequestBodyText(),

                    shareContact =
                        publicContact.toString()
                            .toRequestBodyText(),

                    picture =
                        imagePart
                )

            if(response.isSuccessful) {

                val updatedPost =
                    response.body()
                        ?.data
                        ?.toItemPost()

                if(updatedPost != null) {

                    Result.success(updatedPost)

                } else {

                    Result.failure(
                        Exception("Post not found")
                    )
                }

            } else {

                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "Error updating post"
                    )
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun deletePost(
        postId: Int
    ): Result<String> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.failure(
                Exception(
                    "Esta función requiere conexión con el servidor."
                )
            )
        }

        return try {

            val response =
                api.deletePost(postId)

            if(response.isSuccessful) {

                Result.success(
                    response.body()?.message
                        ?: "Publicación eliminada"
                )

            } else {

                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "Error al eliminar publicación"
                    )
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun getMyPosts(): Result<List<ItemPost>> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.success(
                MockStore.posts.filter {
                    it.isMine
                }
            )
        }

        return try {

            val response =
                api.getMyPosts()

            if(response.isSuccessful) {

                val posts =
                    response.body()
                        ?.data
                        ?.map { it.toItemPost() }
                        ?: emptyList()

                Result.success(posts)

            } else {

                Result.failure(
                    Exception("Error loading my posts")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun getBookmarks(): Result<List<ItemPost>> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.success(
                MockStore.posts.filter {
                    it.isBookmarked
                }
            )
        }

        return try {

            val response =
                api.getBookmarks()

            if(response.isSuccessful) {

                Result.success(
                    response.body()
                        ?.data
                        ?.map { it.toItemPost() }
                        ?: emptyList()
                )

            } else {

                Result.failure(
                    Exception("Error loading bookmarks")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun toggleBookmark(
        postId: Int
    ): Result<String> {

        if (AppConfig.USE_MOCK_DATA) {

            val idx =
                MockStore.posts.indexOfFirst {
                    it.id == postId
                }

            if (idx != -1) {

                MockStore.posts[idx] =
                    MockStore.posts[idx].copy(
                        isBookmarked =
                            !MockStore.posts[idx].isBookmarked
                    )
            }

            return Result.success("OK")
        }

        return try {

            val response =
                api.toggleBookmark(postId)

            if(response.isSuccessful) {

                Result.success(
                    response.body()?.message
                        ?: "Operación realizada"
                )

            } else {

                Result.failure(
                    Exception("Error al guardar publicación")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun reportPost(
        postId: Int,
        reason: String
    ): Result<String> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.success("")
        }

        return try {

            val response =
                api.reportPost(
                    postId = postId,
                    reason = reason
                )

            if(response.isSuccessful) {

                Result.success(
                    response.body()?.message
                        ?: "Publicación reportada"
                )

            } else {

                Result.failure(
                    Exception("Error al reportar publicación")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun completePost(
        postId: Int
    ): Result<String> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.failure(
                Exception(
                    "Esta función requiere conexión con el servidor."
                )
            )
        }

        return try {

            val response =
                api.completePost(postId)

            if(response.isSuccessful) {

                Result.success(
                    response.body()?.message
                        ?: "Publicación completada"
                )

            } else {

                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "Error al completar publicación"
                    )
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }
}