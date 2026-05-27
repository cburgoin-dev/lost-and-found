package com.example.lostfoundapp.utils

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

fun uriToMultipart(
    context: Context,
    uri: Uri
): MultipartBody.Part {

    val contentResolver =
        context.contentResolver

    val inputStream =
        contentResolver.openInputStream(uri)
            ?: throw Exception("No se pudo abrir la imagen")

    val file =
        File(
            context.cacheDir,
            "upload_image.jpg"
        )

    val outputStream =
        FileOutputStream(file)

    inputStream.copyTo(outputStream)

    inputStream.close()
    outputStream.close()

    val requestFile =
        file.asRequestBody(
            "image/*".toMediaTypeOrNull()
        )

    return MultipartBody.Part.createFormData(
        "picture",
        file.name,
        requestFile
    )
}