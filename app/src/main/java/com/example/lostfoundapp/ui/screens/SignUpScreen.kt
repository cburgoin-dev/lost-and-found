package com.example.lostfoundapp.ui.screens

import android.util.Log

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll

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
import androidx.compose.material.icons.outlined.Call
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

    var phone by remember {
        mutableStateOf("")
    }

    var nameError by remember {
        mutableStateOf<String?>(null)
    }

    var emailError by remember {
        mutableStateOf<String?>(null)
    }

    var passwordError by remember {
        mutableStateOf<String?>(null)
    }

    var phoneError by remember {
        mutableStateOf<String?>(null)
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
                    .height(462.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(CardWhite),
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )
                        .padding(
                            start = 28.dp,
                            end = 28.dp,
                            top = 28.dp
                        )
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
                        errorMessage = nameError,
                        onValueChange = {
                            fullname = it
                            nameError = null
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
                        errorMessage = emailError,
                        onValueChange = {
                            email = it
                            emailError = null
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
                        errorMessage = passwordError,
                        onValueChange = {
                            password = it
                            passwordError = null
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        BasicText(
                            text = "Teléfono",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    AuthInput(
                        value = phone,
                        placeholder = "Ingresa tu teléfono",
                        leadingIcon = Icons.Outlined.Call,
                        errorMessage = phoneError,
                        onValueChange = {
                            phone = it
                            phoneError = null
                        }
                    )
                }

                Column(
                    modifier = Modifier.padding(
                        start = 28.dp,
                        end = 28.dp,
                        bottom = 28.dp
                    )
                ) {

                    Spacer(modifier = Modifier.height(24.dp))

                    PrimaryButton(
                        text = "Crear cuenta",

                        onClick = {

                            nameError=
                                when{
                                    fullname.isBlank() ->
                                        "El nombre es obligatorio"
                                    fullname.length <4 ->
                                        "El nombre debe tener al menos 4 caracteres"
                                    else -> null
                                }
                            emailError =
                                when {
                                    email.isBlank() ->
                                        "El correo es obligatorio"

                                    !android.util.Patterns.EMAIL_ADDRESS
                                        .matcher(email)
                                        .matches() ->
                                        "Correo inválido"

                                    else -> null
                                }

                            passwordError =
                                when {
                                    password.isBlank() ->
                                        "La contraseña es obligatoria"

                                    password.length < 6 ->
                                        "La contraseña debe tener al menos 6 caracteres"

                                    else -> null
                                }

                            phoneError =
                                when{
                                    phone.isBlank() ->
                                        "El telefono es obligatorio"    //no se si sea obligatorio en el diseño actual
                                    !phone.matches(Regex("^\\d{10}$")) ->
                                        "El telefono debe contener exactamente 10 números"
                                    else -> null
                                }

                            if (
                                nameError == null &&
                                emailError == null &&
                                passwordError == null &&
                                phoneError == null
                            ) {
                                authViewModel.signup(
                                    name = fullname,
                                    email = email,
                                    password = password,
                                    phone = phone
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    BasicText(
                        text = "Iniciar sesión",

                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable{
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
}

@Preview
@Composable
fun SignUpScreenPreview(){

    SignUpScreen(
        onLoginClick = {},
        onSignupSuccess = {}
    )
}