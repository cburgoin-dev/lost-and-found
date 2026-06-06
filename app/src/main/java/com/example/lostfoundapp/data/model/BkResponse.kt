package com.example.lostfoundapp.data.model

data class BkResponse<T> (
    val data: T? = null,
    val message: String? = null
)

sealed class ApiResult<out T> {
    data class Success<T>(
        val data: T? = null,
        val message: String? = null
    ) : ApiResult<T>()

    data class Error<T>(
        val message: String
    ) : ApiResult<Nothing>()
}