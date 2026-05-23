package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun DescriptionInput(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChange,

        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(
                1.dp,
                BorderGray,
                RoundedCornerShape(16.dp)
            )
            .background(
                androidx.compose.ui.graphics.Color.White,
                RoundedCornerShape(16.dp)
            ),

        textStyle = TextStyle(
            fontSize = 16.sp
        ),

        cursorBrush = SolidColor(TextGray),

        decorationBox = { innerTextField ->

            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp)
            ) {

                if (value.isEmpty()) {

                    androidx.compose.material3.Text(
                        text = placeholder,
                        color = TextGray,
                        fontSize = 16.sp
                    )
                }

                innerTextField()
            }
        }
    )
}