package com.example.lostfoundapp.data.repository

import android.content.Context
import android.net.Uri

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.data.toItemPost
import com.example.lostfoundapp.utils.toRequestBodyText
import com.example.lostfoundapp.utils.uriToMultipart

class PostsRepository(
    private val sessionManager: SessionManager
) {

    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun getPosts(): Result<List<ItemPost>> {

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
        reportType: ReportType,
        objectName: String,
        description: String,
        locationId: Int,
        categoryId: Int,
        date: String,
        publicContact: Boolean,
        selectedImageUri: Uri?
    ): Result<Unit> {

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
                        if(reportType == ReportType.LOST)
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
}