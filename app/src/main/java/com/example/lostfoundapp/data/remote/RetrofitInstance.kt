package com.example.lostfoundapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()

        .baseUrl("https://lostandfound-api-dev-g7m8ub.laravel.cloud/")
        .addConverterFactory(
            ScalarsConverterFactory.create()
        )
        .addConverterFactory(
            GsonConverterFactory.create()
        )

        .build()

    val api: ApiService =
        retrofit.create(ApiService::class.java)
}