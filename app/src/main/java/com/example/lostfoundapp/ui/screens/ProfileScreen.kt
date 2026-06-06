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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.ConfirmationBottomSheet
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.components.profile.ProfileHeader
import com.example.lostfoundapp.ui.components.profile.ProfileMenuCard
import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.LostActionCardForeground
import com.example.lostfoundapp.ui.viewmodel.AuthViewModel
import com.example.lostfoundapp.ui.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    currentRoute: String?,
    hasUnreadActivity: Boolean,

    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick:  () ->  Unit,

    onEditProfileClick: () -> Unit,
    onMyPostsClick: () -> Unit,
    onSavedPostsClick: () -> Unit
) {

    val context = LocalContext.current

    val authViewModel = remember {
        AuthViewModel(
            SessionManager(context)
        )
    }

    LaunchedEffect(
        authViewModel.requestSuccsess
    ) {
        if(authViewModel.requestSuccsess) {
            onLogoutClick()
        }
    }

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
                    userName = userViewModel.userName,
                    email = userViewModel.email,
                    phone = userViewModel.phone,
                    profileImageUri = userViewModel.profileImageUri
                )

                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(
                    text = "Editar perfil",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onEditProfileClick
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Mi contenido",
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
                    onClick = {
                        onMyPostsClick()
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                ProfileMenuCard(
                    title = "Publicaciones guardadas",
                    icon = Icons.Outlined.Bookmark,
                    onClick = {
                        onSavedPostsClick()
                    }
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
                hasUnreadActivity = hasUnreadActivity,
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
                authViewModel.logout()
                // showMessage( authViewModel.requestSuccsess, authViewModel.message )
                showLogoutConfirmation = false
            },

            onDismiss = {
                showLogoutConfirmation = false
            }
        )
    }
}