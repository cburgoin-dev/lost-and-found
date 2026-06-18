package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.response.ApiResult
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.utils.extractErrorMessage
import retrofit2.HttpException
import java.io.IOException

import com.example.lostfoundapp.data.config.AppConfig

class AuthRepository(
    private val sessionManager: SessionManager
) {
    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun logout() : Result<Unit> {

        if (AppConfig.USE_MOCK_DATA) {

            sessionManager.clearSession()

            return Result.success(Unit)
        }

        return try {

            api.logout()

            sessionManager.clearSession()

            Result.success(Unit)

        } catch (e: HttpException) {

            Result.failure(
                Exception(
                    extractErrorMessage(e)
                )
            )

        } catch (e: IOException) {

            Result.failure(
                Exception(
                    "No hay conexión a internet"
                )
            )

        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        if (AppConfig.USE_MOCK_DATA) {

            sessionManager.saveToken("mock-token")

            return Result.success(Unit)
        }

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
        password: String,
        phone: String
    ): Result<Unit> {

        if (AppConfig.USE_MOCK_DATA) {

            sessionManager.saveToken("mock-token")

            return Result.success(Unit)
        }

        return try {

            val signupResponse =
                api.signup(
                    name = name,
                    email = email,
                    password = password,
                    phone = phone
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