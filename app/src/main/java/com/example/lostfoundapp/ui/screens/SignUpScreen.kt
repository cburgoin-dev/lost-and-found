package com.example.lostfoundapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.ui.components.CustomInput
import com.example.lostfoundapp.ui.components.DottedButton
import com.example.lostfoundapp.ui.components.PasswordInput
import com.example.lostfoundapp.ui.components.Roboto
import com.example.lostfoundapp.ui.components.TransparentButton
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen() {

    var fullname by remember {
        mutableStateOf("")
    }

    var studentId by remember {
        mutableStateOf("")
    }

    var career by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            BasicText(
                text = "Sign Up",
                style = TextStyle(
                    fontFamily = Roboto,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(40.dp))
            CustomInput(
                value = fullname,
                placeholder = "Enter your full name",
                onValueChange = {
                    fullname = it
                }
            )

            /*
            Spacer(modifier = Modifier.height(4.dp))

            CustomInput(
                value = studentId,
                placeholder = "Enter your student id",
                onValueChange = {
                    studentId = it
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            CustomInput(
                value = career,
                placeholder = "Enter your career/major",
                onValueChange = {
                    career = it
                }
            )
            */
            Spacer(modifier = Modifier.height(16.dp))
            CustomInput(
                value = email,
                placeholder = "Enter your email",
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInput(
                value = password,
                placeholder = "Enter your password",
                onValueChange = {
                    password = it
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            DottedButton(
                bgColor = Color(0xFF03A2A6),
                dotsColor = Color(0xFF0378A6),
                text = "Create Account",
                onClick = {
/*
                    scope.launch {

                        try {

                            val response =
                                RetrofitInstance.api.signup(
                                    email,
                                    password,
                                    fullname
                                )

                            if(response.success){

                                println("USUARIO CREADO")

                            }else{

                                println("ERROR")
                            }

                        }catch(e: Exception){

                            println(e.message)
                        }
                    }*/
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TransparentButton(
                text = "Login",
                onClick = {

                }
            )
        }
    }
}

@Preview
@Composable
fun SignUpPreview(){
    SignUpScreen()
}
