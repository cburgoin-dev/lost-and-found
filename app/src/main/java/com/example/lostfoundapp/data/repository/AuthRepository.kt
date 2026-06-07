package com.example.lostfoundapp.data.repository


import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.response.ApiResult
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.utils.extractErrorMessage
import retrofit2.HttpException
import java.io.IOException

class AuthRepository(
    private val sessionManager: SessionManager
) {
    private val api =
        RetrofitInstance.createApi(
            sessionManager
        )

    suspend fun logout() : ApiResult<Unit> {
        return try {
            val response = api.logout()
            sessionManager.clearSession()
            ApiResult.Success(
                data = null,
                message = response.message
            )
        } catch (e: HttpException) {
            ApiResult.Error<Unit>(
                extractErrorMessage(e)
            )
        } catch (e: IOException) {
            ApiResult.Error<Unit>(
                "No hay conexión a internet"
            )
        }
    }

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
        password: String,
        phone: String
    ): Result<Unit> {

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