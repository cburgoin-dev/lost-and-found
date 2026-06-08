package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.mapper.toUser
import com.example.lostfoundapp.data.model.User
import com.example.lostfoundapp.data.remote.RetrofitInstance

class UserRepository(
    sessionManager: SessionManager
) {

    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun getUser():
        Result<User> {

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
}