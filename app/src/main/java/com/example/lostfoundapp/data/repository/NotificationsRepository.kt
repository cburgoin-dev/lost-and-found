package com.example.lostfoundapp.data.repository

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.model.ApiResult
import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.utils.extractErrorMessage
import retrofit2.HttpException

class NotificationsRepository (
    private val sessionManager: SessionManager
) {

    private val api = RetrofitInstance.createApi(sessionManager)

    suspend fun getNotifications() : ApiResult<List<Notification>>{
        return try {
            val response = api.getNotifications()
            ApiResult.Success(
                data = response.data
            )
        } catch (e : HttpException){
            ApiResult.Error<Nothing>(
                message = extractErrorMessage(e)
            )
        }
    }
}