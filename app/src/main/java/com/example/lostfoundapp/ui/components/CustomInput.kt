package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.ui.theme.AccentBlue

import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.PlaceholderGray

@Composable
fun CustomInput(
    value: String,
    placeholder: String,
    isError: Boolean = false,
    errorMessage: String = "Campo obligatorio",
    onValueChange: (String) -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor =
        when {
            isError -> Color.Red
            isFocused -> AccentBlue
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            1.dp,
                            borderColor,
                            RoundedCornerShape(16.dp)
                        )
                        .background(
                            Color.White,
                            RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 16.dp),

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
        )

        if(isError) {

            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.padding(
                    start = 4.dp,
                    top = 4.dp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomInputPreview() {

    CustomInput(
        value = "",
        placeholder = "Campo",
        isError = true,
        onValueChange = {}
    )
}
