package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.navigation.Routes
import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.ConfirmationBottomSheet
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.components.profile.ProfileHeader
import com.example.lostfoundapp.ui.components.profile.ProfileMenuCard
import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.LostActionCardForeground

@Composable
fun ProfileScreen(
    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit,
    onEditProfileClick: () -> Unit,
) {

    var showLogoutConfirmation by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),

            contentPadding = PaddingValues(
                start = 24.dp,
                top = 32.dp,
                end = 24.dp,
                bottom = 140.dp
            ),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

                Spacer(modifier = Modifier.height(48.dp))

                ProfileHeader(
                    userName = "Cristian Burgoin",
                    email = "cristian@uabcs.mx",
                    phone = "612 123 4567",
                )

                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(
                    text = "Editar perfil",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onEditProfileClick
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Mi cuenta",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = BorderGray,
                )

                Spacer(modifier = Modifier.height(24.dp))

                ProfileMenuCard(
                    title = "Mis publicaciones",
                    icon = Icons.Filled.Description,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(12.dp))

                ProfileMenuCard(
                    title = "Publicaciones guardadas",
                    icon = Icons.Outlined.Bookmark,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(48.dp))

                PrimaryButton(
                    text = "Cerrar sesión",
                    backgroundColor = LostActionCardForeground,
                    onClick = {
                        showLogoutConfirmation = true
                    }
                )
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

    if(showLogoutConfirmation) {

        ConfirmationBottomSheet(
            title = "Cerrar sesión",
            description = "¿Estás seguro de que deseas cerrar tu sesión?",
            buttonText = "Cerrar sesión",
            buttonColor = LostActionCardForeground,

            onConfirm = {

                // BACKEND, AQUI DEBERIA MANDAR A INICIAR SESIÓN

                showLogoutConfirmation = false
            },

            onDismiss = {
                showLogoutConfirmation = false
            }
        )
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
        onProfileClick = {},
        onEditProfileClick = {}
    )
}