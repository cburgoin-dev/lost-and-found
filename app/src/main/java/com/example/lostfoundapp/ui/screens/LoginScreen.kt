package com.example.lostfoundapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.R
import com.example.lostfoundapp.data.remote.RetrofitInstance.api
import com.example.lostfoundapp.ui.components.AuthInput
import com.example.lostfoundapp.ui.components.AuthPasswordInput
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.components.TransparentButton
import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.DarkOverlay
import com.example.lostfoundapp.ui.theme.GoldAccent
import com.example.lostfoundapp.ui.theme.TextGray
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    val viewModelScope = rememberCoroutineScope()

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
                .background(
                    DarkOverlay
                )
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
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            BasicText(
                text = "Lost & Found",
                style = TextStyle(
                    color = CardWhite,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicText(
                text = "Encuentra y reporta objetos en el campus",
                style = TextStyle(
                    color = CardWhite.copy(alpha = 0.82f),
                    fontSize = 17.sp
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
                        text = "Iniciar sesión",
                        onClick = {
                            viewModelScope.launch {

                                val response = api.login(
                                    email = "dev@dev.com",
                                    password = "password"
                                )

                                if (response.isSuccessful) {

                                    val token = response.body()

                                    onLoginClick()

                                    Log.d("TOKEN", token ?: "null")

                                } else {

                                    Log.d("LOGIN", "Error")
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        BasicText(
                            text = "¿Olvidaste tu contraseña?",

                            modifier = Modifier.clickable {

                            },

                            style = TextStyle(
                                color = TextGray.copy(alpha = 0.85f),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    BasicText(
                        text = "Crear cuenta",

                        modifier = Modifier.clickable {
                            onSignupClick()
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

@Preview
@Composable
fun LoginScreenPreview(){

    LoginScreen(
        onLoginClick = {},
        onSignupClick = {}
    )
}
