package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.Canvas
import com.example.lostfoundapp.R

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


val Roboto = FontFamily(
    Font(R.font.roboto_extra_bold, FontWeight.ExtraBold)
)
@Composable
fun DottedButton(
    bgColor: Color,
    dotsColor: Color,
    text: String,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(55.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.matchParentSize()
        ) {

            val dotRadius = 6f
            val spacing = 26f

            var row = 0
            var y = 0f

            while (y < size.height) {

                var x = if (row % 2 == 0) 0f else spacing / 2

                while (x < size.width) {

                    drawCircle(
                        color = dotsColor.copy(alpha = 0.1f),
                        radius = dotRadius,
                        center = Offset(x, y)
                    )

                    x += spacing
                }

                y += spacing
                row++
            }
        }

        BasicText(
            text = text,
            style = TextStyle(
                fontFamily = Roboto,
                color = Color(
                    255,
                    green = 255,
                    blue = 255,
                    alpha = 255,
                ),
                fontSize = 20.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DottedButtonPreview() {
    DottedButton(
        bgColor = Color(0xFF03a2a6),
        dotsColor = Color(0xFF0378a6),
        text = "Reservar vuelo",
        onClick = {}
    )
}