package com.example.lostfoundapp.data.repository

import android.content.Context
import android.net.Uri
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.mapper.toUser
import com.example.lostfoundapp.data.model.User
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.utils.toRequestBodyText
import com.example.lostfoundapp.utils.uriToMultipart

import com.example.lostfoundapp.data.config.AppConfig
import com.example.lostfoundapp.data.mock.anaUser

class UserRepository(
    sessionManager: SessionManager
) {

    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun getUser():
        Result<User> {

        if (AppConfig.USE_MOCK_DATA) {
            return Result.success(anaUser)
        }

        return try {

            val response =
                api.getUser()

            if(response.isSuccessful) {

                val user =
                    response.body()
                        ?.data
                        ?.toUser()

                if(user != null) {

                    Result.success(user)

                } else {

                    Result.failure(
                        Exception("User not found")
                    )
                }

            } else {

                Result.failure(
                    Exception("Error loading user")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }
    suspend fun updateUser(
        context: Context,
        name: String,
        phone: String,
        imageUri: Uri?
    ): Result<User> {

        if (AppConfig.USE_MOCK_DATA) {

            return Result.success(
                anaUser.copy(
                    name = name,
                    phone = phone
                )
            )
        }

        return try {

            val response =
                api.updateUser(

                    name =
                        name.toRequestBodyText(),

                    phone =
                        phone.toRequestBodyText(),

                    picture =
                        imageUri?.let {
                            uriToMultipart(
                                context,
                                it
                            )
                        }
                )

            if(response.isSuccessful) {

                val user =
                    response.body()
                        ?.data
                        ?.toUser()

                if(user != null) {

                    Result.success(user)

                } else {

                    Result.failure(
                        Exception("User not found")
                    )
                }

            } else {

                Result.failure(
                    Exception("Error updating user")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }
}