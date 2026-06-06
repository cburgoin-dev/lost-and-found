package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.remote.ApiService
import com.example.lostfoundapp.data.toItemPost

class PostsRepository(
    private val api: ApiService,
    private val sessionManager: SessionManager
) {

    suspend fun getPosts(): Result<List<ItemPost>> {

        return try {

            val token =
                sessionManager.getToken()

            if(token == null) {

                return Result.failure(
                    Exception("No session found")
                )
            }

            val response =
                api.getPosts(
                    "Bearer $token"
                )

            if (response.isSuccessful) {

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

        return try {

            val token =
                sessionManager.getToken()

            if(token == null) {

                return Result.failure(
                    Exception("No session found")
                )
            }

            val response =
                api.getPosts(
                    token = "Bearer $token",
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
}