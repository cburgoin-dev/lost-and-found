package com.example.lostfoundapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()

        .baseUrl("http://10.0.2.2:8000/")
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