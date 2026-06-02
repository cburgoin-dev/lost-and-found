package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lostfoundapp.navigation.Routes
import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.profile.ProfileHeader
import com.example.lostfoundapp.ui.components.profile.ProfileMenuSection

@Composable
fun ProfileScreen(
    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {

            item {

                Spacer(modifier = Modifier.height(32.dp))

                ProfileHeader(
                    userName = "Cristian Burgoin",
                    email = "cristian@uabcs.mx",
                    phone = "612 123 4567",
                    isContactVisible = true,
                    onEditProfileClick = {},
                    onVisibilityChange = {}
                )

                Spacer(modifier = Modifier.height(24.dp))

                ProfileMenuSection()

                Spacer(modifier = Modifier.height(24.dp))

                // LogoutSection()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            AppBottomBar(
                currentRoute = currentRoute,
                onHomeClick = onHomeClick,
                onSearchClick = onSearchClick,
                onActivityClick = onActivityClick,
                onProfileClick = onProfileClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {

    ProfileScreen(
        currentRoute = Routes.Profile.route,
        onHomeClick = {},
        onSearchClick = {},
        onActivityClick = {},
        onProfileClick = {}
    )
}