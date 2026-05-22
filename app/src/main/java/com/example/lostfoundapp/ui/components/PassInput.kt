package com.example.lostfoundapp.ui.components

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordInput(

    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit

) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

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
                fontSize = 20.sp
            ),

            decorationBox = { innerTextField ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .border(
                            1.dp,
                            Color.LightGray,
                            RoundedCornerShape(8.dp)
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
                                    color = Color.LightGray,
                                    fontSize = 20.sp
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

                            contentDescription = "Mostrar contraseña"
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordInputPreview() {

    PasswordInput(
        value = "jiiojnjjn",
        placeholder = "Ingresa tu contraseña",
        onValueChange = {}
    )
}