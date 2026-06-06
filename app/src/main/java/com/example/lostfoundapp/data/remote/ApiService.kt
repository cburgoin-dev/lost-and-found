package com.example.lostfoundapp.data.remote

import com.example.lostfoundapp.data.model.BkResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

import com.example.lostfoundapp.data.response.CreateRequestResponse
import com.example.lostfoundapp.data.response.GetRequestsResponse
import com.example.lostfoundapp.data.response.PostsResponse
import retrofit2.http.DELETE

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

    @DELETE("api/logout")
    suspend fun logout( ): BkResponse<Unit>

    @FormUrlEncoded
    @POST("api/sign-up")
    suspend fun signup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String

    ): Response<String>

    @Multipart
    @POST("api/posts")
    suspend fun createPost(

        @Header("Authorization")
        token: String,

        @Part("type")
        type: RequestBody,

        @Part("title")
        title: RequestBody,

        @Part("description")
        description: RequestBody,

        @Part("location_id")
        locationId: RequestBody,

        @Part("category_id")
        categoryId: RequestBody,

        @Part("incident_date")
        incidentDate: RequestBody,

        @Part("share_my_data")
        shareContact: RequestBody,

        @Part picture: MultipartBody.Part?



    ): Response<String>

    @GET("api/posts")
    suspend fun getPosts(
        @Header("Authorization")
        token: String
    ): Response<PostsResponse>

    @GET("api/posts")
    suspend fun getPosts(
        @Header("Authorization")
        token: String,

        @Query("category_id")
        categoryId: Int? = null,

        @Query("location_id")
        locationId: Int? = null,

        @Query("time")
        time: String? = null
    ): Response<PostsResponse>

    @Multipart
    @POST("api/requests")
    suspend fun createRequest(

        @Header("Authorization")
        token: String,

        @Part("post_id")
        postId: RequestBody,

        @Part("content")
        content: RequestBody,

        @Part("message")
        message: RequestBody
    ): Response<CreateRequestResponse>

    @GET("api/requests")
    suspend fun getRequests(
        @Header("Authorization")
        token: String,

    ): Response<GetRequestsResponse>

    @PATCH("api/requests/{id}/accept")
    suspend fun approveRequest(
        @Header("Authorization")
        token: String,
        @Path("id")
        requestId : Int
        ): Response<GetRequestsResponse>

    @PATCH("api/requests/{id}/decline")
    suspend fun declineRequest(
        @Header("Authorization")
        token: String,
        @Path("id")
        requestId : Int
    ): Response<GetRequestsResponse>
}
