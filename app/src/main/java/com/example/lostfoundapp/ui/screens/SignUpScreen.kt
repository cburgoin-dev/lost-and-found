package com.example.lostfoundapp.ui.theme.screens


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
import com.example.lostfoundapp.ui.theme.components.CustomInput
import com.example.lostfoundapp.ui.theme.components.DottedButton
import com.example.lostfoundapp.ui.theme.components.PasswordInput
import com.example.lostfoundapp.ui.theme.components.Roboto
import com.example.lostfoundapp.ui.theme.components.TransparentButton

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

            Spacer(modifier = Modifier.height(4.dp))
            CustomInput(
                value = email,
                placeholder = "Enter your email",
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

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
                text = "Login",
                onClick = {

                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TransparentButton(
                text = "Create Account",
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
