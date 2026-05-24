package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.PrimaryDarkBlue

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(32.dp)
            )
            .background(
                PrimaryDarkBlue,
                RoundedCornerShape(32.dp)
            )
            .clickable {
                onClick()
            },

        contentAlignment = Alignment.Center
    ) {

        BasicText(
            text = text,

            style = TextStyle(
                color = CardWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
            )
        )
    }
}