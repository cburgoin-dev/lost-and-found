package com.example.lostfoundapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun TransparentButton(
    text: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .width(width = 180.dp)
            .height(55.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        BasicText(
            text = text,
            style = TextStyle(
                fontFamily = Roboto,
                color = Color(0xFF03a2a6),
                fontSize = 20.sp
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GrayButtonPreview() {
    TransparentButton(
        text = "Reservar vuelo",
        onClick = {}
    )
}