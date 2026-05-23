package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.BackgroundGray
import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun UploadImageCard(
    isError: Boolean = false,
    errorMessage: String = "Foto obligatoria",
    onClick: () -> Unit
) {

    val borderColor =
        if(isError)
            Color.Red
        else
            BorderGray

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(
                    border = BorderStroke(
                        1.dp,
                        borderColor
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    BackgroundGray,
                    RoundedCornerShape(16.dp)
                )
                .clickable {
                    onClick()
                },

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = null,
                tint = TextGray
            )

            Text(
                text = "Toca para subir foto",
                color = TextGray,
                fontSize = 16.sp
            )
        }

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