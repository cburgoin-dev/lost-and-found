package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.data.mapper.toRequest
import com.example.lostfoundapp.utils.toRequestBodyText

import com.example.lostfoundapp.data.config.AppConfig
import com.example.lostfoundapp.data.mock.MockRequestStore
import com.example.lostfoundapp.data.mock.mockRequests
import com.example.lostfoundapp.data.model.RequestStatus

class RequestsRepository(
    sessionManager: SessionManager
) {

    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun getRequests(): Result<List<Request>> {

        if (AppConfig.USE_MOCK_DATA) {
            return Result.success(
                MockRequestStore.requests
            )
        }

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

        if (AppConfig.USE_MOCK_DATA) {
            return Result.success(Unit)
        }

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

        if (AppConfig.USE_MOCK_DATA) {

            val index =
                MockRequestStore.requests.indexOfFirst {
                    it.id == requestId
                }

            if (index != -1) {

                MockRequestStore.requests[index] =
                    MockRequestStore.requests[index].copy(
                        status = RequestStatus.APPROVED
                    )
            }

            return Result.success(Unit)
        }

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

        if (AppConfig.USE_MOCK_DATA) {

            val index =
                MockRequestStore.requests.indexOfFirst {
                    it.id == requestId
                }

            if (index != -1) {

                MockRequestStore.requests[index] =
                    MockRequestStore.requests[index].copy(
                        status = RequestStatus.REJECTED
                    )
            }

            return Result.success(Unit)
        }

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