package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.data.toRequest
import com.example.lostfoundapp.utils.toRequestBodyText

class RequestsRepository(
    private val sessionManager: SessionManager
) {

    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun getRequests(): Result<List<Request>> {

        return try {

            val response =
                api.getRequests()

            if(response.isSuccessful) {

                val requests =
                    response.body()
                        ?.data
                        ?.map { it.toRequest() }
                        ?: emptyList()

                Result.success(requests)

            } else {

                Result.failure(
                    Exception("Error loading requests")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun createRequest(
        postId: Int,
        content: String,
        message: String
    ): Result<Unit> {

        return try {

            val response =
                api.createRequest(

                    postId =
                        postId.toString()
                            .toRequestBodyText(),

                    content =
                        content.toRequestBodyText(),

                    message =
                        message.toRequestBodyText()
                )

            if(response.isSuccessful) {

                Result.success(Unit)

            } else {

                Result.failure(
                    Exception(
                        response.errorBody()?.string()
                            ?: "Error creating request"
                    )
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun approveRequest(
        requestId: Int
    ): Result<Unit> {

        return try {

            val response =
                api.approveRequest(
                    requestId
                )

            if(response.isSuccessful) {

                Result.success(Unit)

            } else {

                Result.failure(
                    Exception("Error approving request")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun declineRequest(
        requestId: Int
    ): Result<Unit> {

        return try {

            val response =
                api.declineRequest(
                    requestId
                )

            if(response.isSuccessful) {

                Result.success(Unit)

            } else {

                Result.failure(
                    Exception("Error declining request")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }
}