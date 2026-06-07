package com.example.lostfoundapp.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.CardWhite
import com.example.lostfoundapp.ui.theme.FoundActionCardForeground

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = FoundActionCardForeground,
    textColor: Color = CardWhite,
    height: Dp = 58.dp,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(18.dp)
            )
            .background(
                if(enabled)
                    backgroundColor
                else
                    backgroundColor.copy(alpha = 0.5f),
                RoundedCornerShape(18.dp)
            )
            .clickable(
                enabled = enabled
            ) {
                onClick()
            },

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,

            style = TextStyle(
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
            )
        )
    }
}