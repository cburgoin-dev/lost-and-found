package com.example.lostfoundapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

import com.example.lostfoundapp.ui.components.AuthInput
import com.example.lostfoundapp.ui.components.BackButton
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.TextGray
import com.example.lostfoundapp.ui.viewmodel.EditProfileViewModel
import com.example.lostfoundapp.ui.viewmodel.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun EditProfileScreen(
    userViewModel: UserViewModel,
    editProfileViewModel: EditProfileViewModel,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onChangePhotoClick: (Uri) -> Unit
) {

    LaunchedEffect(userViewModel.user) {

        userViewModel.user?.let {

            editProfileViewModel.startEditing(
                currentName = it.name,
                currentPhone = it.phone ?: "",
                currentImage = null
            )
        }
    }

    LaunchedEffect(userViewModel.updateSuccess) {

        if(userViewModel.updateSuccess) {

            delay(250)

            onSaveClick()
        }
    }

    val context = LocalContext.current

    val currentProfileImage =
        editProfileViewModel.profileImageUri
            ?: userViewModel.user?.profileImageUrl

    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            if(uri != null) {

                editProfileViewModel.updateProfileImage(uri)

                onChangePhotoClick(uri)
            }
        }

    val focusManager = LocalFocusManager.current

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(
                WindowInsets.safeDrawing
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .background(HomeHeaderBlue)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    BackButton(
                        onClick = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                            onBackClick()
                        },
                        hasBackground = false,
                        iconColor = Color.White
                    )

                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Editar perfil",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(42.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .windowInsetsPadding(
                        WindowInsets.ime
                    )
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                if (currentProfileImage != null) {

                    SubcomposeAsyncImage(

                        model = currentProfileImage,

                        contentDescription = null,

                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .clickable {
                                imagePickerLauncher.launch("image/*")
                            },

                        contentScale = ContentScale.Crop,

                        loading = {
                            ProfilePlaceholder(
                                size = 120.dp,
                                iconSize = 56.dp
                            )
                        },

                        error = {
                            ProfilePlaceholder(
                                size = 120.dp,
                                iconSize = 56.dp
                            )
                        }
                    )

                } else {

                    ProfilePlaceholder(
                        size = 120.dp,
                        iconSize = 56.dp
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Cambiar foto",
                    color = HomeHeaderBlue,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        imagePickerLauncher.launch("image/*")
                    }
                )

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Nombre completo",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                AuthInput(
                    value = editProfileViewModel.name,
                    placeholder = "Ingresa tu nombre",
                    leadingIcon = Icons.Outlined.Person,

                    onValueChange = {
                        editProfileViewModel.updateName(it)
                    }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Correo electrónico",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Medium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .border(
                            1.dp,
                            BorderGray,
                            RoundedCornerShape(12.dp)
                        )
                        .background(
                            CardWhite,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null,
                        tint = Color.Gray
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Text(
                        text = userViewModel.user?.email ?: "",
                        color = Color.Gray
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Número telefónico",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                AuthInput(
                    value = editProfileViewModel.phone,
                    placeholder = "Ingresa tu teléfono",
                    leadingIcon = Icons.Outlined.Phone,

                    onValueChange = {
                        editProfileViewModel.updatePhone(it)
                    }
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                PrimaryButton(
                    text = "Guardar cambios",

                    onClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        userViewModel.updateUser(

                            context = context,

                            name = editProfileViewModel.name,

                            phone = editProfileViewModel.phone,

                            imageUri = editProfileViewModel.profileImageUri
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ProfilePlaceholder(
    size: Dp = 120.dp,
    iconSize: Dp = 56.dp
) {

    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(Color(0xFFF1F1F1)),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = null,
            tint = TextGray,
            modifier = Modifier.size(iconSize)
        )
    }
}