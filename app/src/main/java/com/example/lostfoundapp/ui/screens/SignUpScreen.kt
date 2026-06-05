package com.example.lostfoundapp.ui.screens

import android.util.Log

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.local.SessionManager

import com.example.lostfoundapp.ui.components.AuthInput
import com.example.lostfoundapp.ui.components.AuthPasswordInput
import com.example.lostfoundapp.ui.components.PrimaryButton

import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.DarkOverlay
import com.example.lostfoundapp.ui.theme.GoldAccent
import com.example.lostfoundapp.ui.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    onLoginClick: () -> Unit,
    onSignupSuccess: () -> Unit
) {

    var fullname by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val authViewModel = remember {

        AuthViewModel(
            SessionManager(context)
        )
    }

    LaunchedEffect(
        authViewModel.authSuccess
    ) {

        if(authViewModel.authSuccess) {

            onSignupSuccess()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.uabcs_bg),
            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkOverlay)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(R.drawable.uabcs_logo),
                contentDescription = null,

                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            BasicText(
                text = "Universidad Autónoma de Baja California Sur",

                style = TextStyle(
                    color = CardWhite.copy(alpha = 0.82f),
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            BasicText(
                text = "Crear cuenta",
                style = TextStyle(
                    color = CardWhite,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicText(
                text = "Regístrate para reportar objetos perdidos",
                style = TextStyle(
                    color = CardWhite.copy(alpha = 0.82f),
                    fontSize = 17.sp
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(CardWhite)
                    .padding(
                        start = 28.dp,
                        end = 28.dp,
                        top = 28.dp,
                        bottom = 28.dp
                    ),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    BasicText(
                        text = "Nombre completo",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                AuthInput(
                    value = fullname,
                    placeholder = "Ingresa tu nombre completo",
                    leadingIcon = Icons.Outlined.Person,

                    onValueChange = {
                        fullname = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    BasicText(
                        text = "Correo electrónico",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                AuthInput(
                    value = email,
                    placeholder = "Ingresa tu correo",
                    leadingIcon = Icons.Outlined.Email,

                    onValueChange = {
                        email = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    BasicText(
                        text = "Contraseña",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                AuthPasswordInput(
                    value = password,
                    placeholder = "Ingresa tu contraseña",

                    onValueChange = {
                        password = it
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                PrimaryButton(
                    text = "Crear cuenta",

                    onClick = {

                        authViewModel.signup(
                            name = fullname,
                            email = email,
                            password = password
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                BasicText(
                    text = "Iniciar sesión",

                    modifier = Modifier.clickable {
                        onLoginClick()
                    },

                    style = TextStyle(
                        color = GoldAccent,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview(){

    SignUpScreen(
        onLoginClick = {},
        onSignupSuccess = {}
    )
}