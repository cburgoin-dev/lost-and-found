package com.example.lostfoundapp.data.remote

import com.example.lostfoundapp.data.local.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    private const val baseUrl = "http://192.168.1.85:8000/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            ScalarsConverterFactory.create()
        )
        .addConverterFactory(
            GsonConverterFactory.create()
        )

        .build()

    val api: ApiService =
        retrofit.create(ApiService::class.java)

    fun createApi(
        sessionManager: SessionManager
    ): ApiService {

        val client =
            OkHttpClient.Builder()
                .addInterceptor(
                    AuthInterceptor(
                        sessionManager
                    )
                )
                .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                ScalarsConverterFactory.create()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ApiService::class.java)
    }
}