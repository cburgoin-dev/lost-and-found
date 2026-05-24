package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.PlaceholderGray
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun AuthInput(
    value: String,
    placeholder: String,
    leadingIcon: ImageVector,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChange,

        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),

        decorationBox = { innerTextField ->

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
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = TextGray
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {

                    if(value.isEmpty()) {

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
            }
        }
    )
}