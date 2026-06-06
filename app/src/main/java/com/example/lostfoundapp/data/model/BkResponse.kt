package com.example.lostfoundapp.data.model

data class BkResponse<T> (
    val data: T? = null,
    val message: String
)

sealed class ApiResult<out T> {
    data class Success<T>(
        val data : T? = null,
        val message: String
    ) : ApiResult<T>()

    data class Error(
        val message: String
    ) : ApiResult<Unit>()
}