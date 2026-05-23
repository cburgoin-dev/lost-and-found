package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.PlaceholderGray

@Composable
fun PasswordInput(

    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit

) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    BasicTextField(

        value = value,
        onValueChange = onValueChange,

        visualTransformation =
            if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),

        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),

        decorationBox = { innerTextField ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        1.dp,
                        BorderGray,
                        RoundedCornerShape(16.dp)
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 16.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {

                    if (value.isEmpty()) {

                        BasicText(
                            text = placeholder,
                            style = TextStyle(
                                color = PlaceholderGray,
                                fontSize = 16.sp
                            )
                        )
                    }

                    innerTextField()
                }

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {

                    Icon(
                        imageVector =
                            if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,

                        contentDescription = "Mostrar contraseña",
                        tint = PlaceholderGray
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputPreview() {

    PasswordInput(
        value = "",
        placeholder = "Ingresa tu contraseña",
        onValueChange = {}
    )
}