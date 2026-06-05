package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.remote.ApiService

class AuthRepository(
    private val api: ApiService,
    private val sessionManager: SessionManager
) {

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            val response =
                api.login(
                    email = email,
                    password = password
                )

            if(response.isSuccessful) {

                val token =
                    response.body()

                if(token != null) {

                    sessionManager.saveToken(
                        token
                    )

                    Result.success(Unit)

                } else {

                    Result.failure(
                        Exception("Token vacío")
                    )
                }

            } else {

                Result.failure(
                    Exception("Credenciales inválidas")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun signup(
        name: String,
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            val signupResponse =
                api.signup(
                    name = name,
                    email = email,
                    password = password
                )

            if(signupResponse.isSuccessful) {

                val loginResponse =
                    api.login(
                        email = email,
                        password = password
                    )

                if(loginResponse.isSuccessful) {

                    val token =
                        loginResponse.body()

                    if(token != null) {

                        sessionManager.saveToken(
                            token
                        )

                        Result.success(Unit)

                    } else {

                        Result.failure(
                            Exception("Token vacío")
                        )
                    }

                } else {

                    Result.failure(
                        Exception("Error al iniciar sesión")
                    )
                }

            } else {

                Result.failure(
                    Exception("Error al crear cuenta")
                )
            }

        } catch(e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun forgotPassword(
        email: String
    ): Result<Unit> {

        return Result.success(Unit)
    }
}