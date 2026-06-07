package com.example.lostfoundapp.utils

import com.example.lostfoundapp.data.response.BkResponse
import com.google.gson.Gson
import retrofit2.HttpException

fun extractErrorMessage(
    e: HttpException
): String {

    return try {

        val json =
            e.response()
                ?.errorBody()
                ?.string()

        val response =
            Gson().fromJson(
                json,
                BkResponse::class.java
            )

        response?.message
            ?: "Error desconocido"

    } catch (_: Exception) {

        "Error desconocido"
    }
}