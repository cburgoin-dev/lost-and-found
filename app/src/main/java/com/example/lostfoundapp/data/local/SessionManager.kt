package com.example.lostfoundapp.data.local

import android.content.Context

class SessionManager(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "user_session",
            Context.MODE_PRIVATE
        )

    fun saveToken(token: String){

        prefs.edit()
            .putString("token", token)
            .apply()
    }

    fun hasToken(): Boolean{
        return this.getToken() != null
    }

    fun getToken(): String? {

        return prefs.getString(
            "token",
            null
        )
    }

    fun clearSession(){

        prefs.edit().clear().apply()
    }
}