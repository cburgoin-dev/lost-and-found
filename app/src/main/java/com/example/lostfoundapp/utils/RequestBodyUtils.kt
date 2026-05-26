package com.example.lostfoundapp.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toRequestBodyText(): RequestBody {

    return this.toRequestBody(
        "text/plain".toMediaType()
    )
}