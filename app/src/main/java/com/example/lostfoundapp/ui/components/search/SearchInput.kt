package com.example.lostfoundapp.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.PlaceholderGray

@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,

        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),

        modifier = Modifier.fillMaxWidth(),

        decorationBox = { innerTextField ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 16.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = PlaceholderGray
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier.weight(1f)
                ) {

                    if (value.isEmpty()) {

                        Text(
                            text = "Buscar objeto o ubicación...",
                            color = PlaceholderGray,
                            fontSize = 16.sp
                        )
                    }

                    innerTextField()
                }

                if(value.isNotEmpty()) {

                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Limpiar búsqueda",
                        tint = PlaceholderGray,

                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onValueChange("")
                        }
                    )
                }
            }
        }
    )
}