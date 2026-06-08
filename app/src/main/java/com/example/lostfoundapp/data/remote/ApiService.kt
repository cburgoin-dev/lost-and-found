package com.example.lostfoundapp.data.remote

import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.response.BkResponse
import com.example.lostfoundapp.data.response.CategoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

import com.example.lostfoundapp.data.response.CreateRequestResponse
import com.example.lostfoundapp.data.response.GetRequestsResponse
import com.example.lostfoundapp.data.response.LocationsResponse
import com.example.lostfoundapp.data.response.MessageResponse
import com.example.lostfoundapp.data.response.NotificationResponse
import com.example.lostfoundapp.data.response.PostsResponse
import com.example.lostfoundapp.data.response.UpdateUserResponse
import com.example.lostfoundapp.data.response.UserResponse
import retrofit2.http.DELETE

interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<String>

    @FormUrlEncoded
    @POST("api/sign-up")
    suspend fun signup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String
    ): Response<String>

    @DELETE("api/logout")
    suspend fun logout():
        BkResponse<Unit>

    @GET("api/user")
    suspend fun getUser():
        Response<UserResponse>

    @GET("api/locations")
    suspend fun getLocations():
        Response<LocationsResponse>

    @GET("api/categories")
    suspend fun getCategories():
        Response<CategoriesResponse>

    @Multipart
    @POST("api/posts")
    suspend fun createPost(

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
    suspend fun getPosts():
        Response<PostsResponse>

    @GET("api/posts")
    suspend fun getPosts(

        @Query("category_id")
        categoryId: Int? = null,

        @Query("location_id")
        locationId: Int? = null,

        @Query("time")
        time: String? = null

    ): Response<PostsResponse>

    @PATCH("api/posts/{id}/complete")
    suspend fun completePost(
        @Path("id")
        postId: Int
    ): Response<MessageResponse>

    @DELETE("api/posts/{id}")
    suspend fun deletePost(
        @Path("id")
        postId: Int
    ): Response<MessageResponse>

    @POST("api/posts/{id}/bookmark")
    suspend fun toggleBookmark(
        @Path("id")
        postId: Int
    ): Response<MessageResponse>

    @FormUrlEncoded
    @POST("api/posts/{id}/report")
    suspend fun reportPost(

        @Path("id")
        postId: Int,

        @Field("reason")
        reason: String

    ): Response<MessageResponse>

    @Multipart
    @POST("api/requests")
    suspend fun createRequest(

        @Part("post_id")
        postId: RequestBody,

        @Part("content")
        content: RequestBody,

        @Part("message")
        message: RequestBody

    ): Response<CreateRequestResponse>

    @GET("api/requests")
    suspend fun getRequests():
        Response<GetRequestsResponse>

    @PATCH("api/requests/{id}/accept")
    suspend fun approveRequest(
        @Path("id")
        requestId : Int
    ): Response<GetRequestsResponse>

    @PATCH("api/requests/{id}/decline")
    suspend fun declineRequest(
        @Path("id")
        requestId : Int
    ): Response<GetRequestsResponse>

    @GET("api/notifications")
    suspend fun getNotifications(
    ): BkResponse<List<NotificationResponse>>

    @PATCH("api/notifications/{id}/read")
    suspend fun markNotificationAsRead(
        @Path("id")
        notificationId: Int
    ): Response<MessageResponse>

    @Multipart
    @PATCH("api/user")
    suspend fun updateUser(

        @Part("name")
        name: RequestBody,

        @Part("phone")
        phone: RequestBody,

        @Part
        picture: MultipartBody.Part?

    ): Response<UpdateUserResponse>

    @GET("api/user/posts")
    suspend fun getMyPosts():
        Response<PostsResponse>

    @GET("api/user/saved-posts")
    suspend fun getBookmarks():
        Response<PostsResponse>
}
