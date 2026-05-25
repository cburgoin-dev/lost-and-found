package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.lostfoundapp.navigation.Routes
import com.example.lostfoundapp.ui.components.AppBottomBar

@Composable
fun SearchScreen(
    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text("Search Screen")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            AppBottomBar(
                currentRoute = currentRoute,
                onHomeClick = onHomeClick,
                onSearchClick = onSearchClick,
                onNotificationsClick = onNotificationsClick,
                onProfileClick = onProfileClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {

    SearchScreen(
        currentRoute = Routes.Search.route,
        onHomeClick = {},
        onSearchClick = {},
        onNotificationsClick = {},
        onProfileClick = {}
    )
}