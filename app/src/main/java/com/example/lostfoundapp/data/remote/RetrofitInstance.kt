package com.example.lostfoundapp.data.remote

import com.example.lostfoundapp.data.local.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    /*
     * IMPORTANTE:
     * Antes de hacer push verificar que
     * USE_LOCAL_SERVER = false
     */

    private const val USE_LOCAL_SERVER = false

    private const val CLOUD_URL =
        "https://lostandfound-api-dev-g7m8ub.laravel.cloud/"

    private const val LOCAL_URL =
        "http://192.168.1.85:8000/" // Manuel local Laravel server

    private val baseUrl =
        if(USE_LOCAL_SERVER)
            LOCAL_URL
        else
            CLOUD_URL

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
            .baseUrl(BASE_URL)
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