package com.example.lostfoundapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lostfoundapp.ui.screens.ReportItemScreen
import com.example.lostfoundapp.ui.theme.LostFoundAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            LostFoundAppTheme {

                ReportItemScreen()
            }
        }
    }
}