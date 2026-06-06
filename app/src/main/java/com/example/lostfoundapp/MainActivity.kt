package com.example.lostfoundapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lostfoundapp.data.local.SessionManager

import com.example.lostfoundapp.navigation.AppNavigation
import com.example.lostfoundapp.ui.theme.LostFoundAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val sessionManager by lazy {
            SessionManager(this)
        }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            LostFoundAppTheme {

                AppNavigation(
                    sessionManager = sessionManager
                )
            }
        }
    }
}