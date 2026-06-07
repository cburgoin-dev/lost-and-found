package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.ErrorRed
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.PlaceholderGray
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun AuthInput(
    value: String,
    placeholder: String,
    leadingIcon: ImageVector,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isFocused by interactionSource.collectIsFocusedAsState()

    val hasError = errorMessage != null

    val borderColor =
        when {
            hasError -> ErrorRed
            isFocused -> HomeHeaderBlue
            else -> BorderGray
        }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            interactionSource = interactionSource,

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
                            width = 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(
                            color = CardWhite,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = TextGray
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

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
                }
            }
        )

        if (hasError) {

            Text(
                text = errorMessage,
                color = ErrorRed,
                fontSize = 13.sp,
                modifier = Modifier.padding(
                    start = 4.dp,
                    top = 4.dp
                )
            )
        }
    }
}