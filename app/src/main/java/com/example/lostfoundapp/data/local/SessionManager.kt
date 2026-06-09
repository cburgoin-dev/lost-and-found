package com.example.lostfoundapp.data.local

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class SessionManager(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "user_session",
            Context.MODE_PRIVATE
        )

    private val _isAuthenticated =
        MutableStateFlow(getToken() != null)

    val isAuthenticated =
        _isAuthenticated.asStateFlow()

    fun saveToken(token: String){

        prefs.edit()
            .putString("token", token)
            .apply()

        _isAuthenticated.value = true
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

        CoroutineScope(Dispatchers.IO).launch {
            prefs.edit().clear().apply()
            _isAuthenticated.value = false
        }
    }
}