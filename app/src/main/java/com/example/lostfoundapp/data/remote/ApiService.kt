package com.example.lostfoundapp.data.remote

import com.example.lostfoundapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    /*
    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(

        @Field("email") email: String,
        @Field("password") password: String

    ): LoginResponse

    @FormUrlEncoded
    @POST(value = "signup.php")
    suspend fun signup(
        @Field(value = "email") email: String,
        @Field(value = "password") password: String,
        @Field(value = "fullname") fullname: String
    ): LoginResponse
    //old
    */
    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<String>
}