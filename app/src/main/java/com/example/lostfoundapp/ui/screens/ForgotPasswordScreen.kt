package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.R
import com.example.lostfoundapp.ui.components.AuthInput
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.DarkOverlay
import com.example.lostfoundapp.ui.theme.GoldAccent
import com.example.lostfoundapp.ui.theme.TextGray

import androidx.compose.material3.Icon
import androidx.compose.ui.platform.LocalContext
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.ui.viewmodel.AuthViewModel

@Composable
fun ForgotPasswordScreen(
    onBackToLoginClick: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var emailSent by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val authViewModel = remember {

        AuthViewModel(
            SessionManager(context)
        )
    }

    LaunchedEffect(
        authViewModel.forgotPasswordSuccess
    ) {

        if(authViewModel.forgotPasswordSuccess) {

            emailSent = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(
                R.drawable.uabcs_bg
            ),

            contentDescription = null,

            contentScale = ContentScale.Crop,

            modifier = Modifier.fillMaxSize()
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

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(
                    R.drawable.uabcs_logo
                ),

                contentDescription = null,

                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            BasicText(
                text = "Universidad Autónoma de Baja California Sur",
                style = TextStyle(
                    color = CardWhite.copy(alpha = 0.82f),
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            BasicText(
                text = "¿Olvidaste tu contraseña?",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = CardWhite,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicText(
                text = "Ingresa tu correo institucional para recuperar tu cuenta",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = CardWhite.copy(alpha = 0.82f),
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            Card(
                shape = RoundedCornerShape(28.dp),

                colors = CardDefaults.cardColors(
                    containerColor = CardWhite
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {

                if (!emailSent) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                text = "Correo electrónico",

                                style = TextStyle(
                                    fontSize = 15.sp,
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

                        Spacer(modifier = Modifier.height(24.dp))

                        PrimaryButton(
                            text = "Enviar enlace",

                            onClick = {

                                authViewModel.forgotPassword(
                                    email
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        BasicText(
                            text = "Volver al inicio de sesión",

                            modifier = Modifier.clickable {
                                onBackToLoginClick()
                            },

                            style = TextStyle(
                                color = GoldAccent,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 28.dp,
                                end = 28.dp,
                                top = 36.dp,
                                bottom = 36.dp,
                            ),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,

                            contentDescription = null,

                            tint = Color(0xFF6BBF73),

                            modifier = Modifier.size(72.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        BasicText(
                            text = "Revisa tu correo institucional para continuar con la recuperación de contraseña",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        BasicText(
                            text = "Te hemos enviado un enlace para restablecer tu contraseña. Recuerda revisar tu carpeta de spam si no lo encuentras.",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = TextGray,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        BasicText(
                            text = "Volver al inicio de sesión",

                            modifier = Modifier.clickable {
                                onBackToLoginClick()
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
    }
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {

    ForgotPasswordScreen(
        onBackToLoginClick = {}
    )
}