package com.example.lostfoundapp.data.remote

import com.example.lostfoundapp.data.local.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val token = sessionManager.getToken()

        val request = chain.request()
            .newBuilder()
            .apply {

                token?.let {

                    addHeader(
                        "Authorization",
                        "Bearer $it"
                    )
                }
            }
            .build()

        return chain.proceed(request)
    }
}