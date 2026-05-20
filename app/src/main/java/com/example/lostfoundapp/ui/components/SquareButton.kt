package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.R

@Composable
fun SquareButton(
    text: String,
    bgColor: Color,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .width(170.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .padding(16.dp)
            .clickable{
                onClick()
            },

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.height(64.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SquareButtonPreview() {

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        SquareButton(
            text = "Lost something",
            bgColor = Color(0xFF2CBEC0),
            icon = painterResource(R.drawable.ic_launcher_foreground),
            onClick = {},
            modifier = Modifier.weight(1f)
        )

        SquareButton(
            text = "Found something",
            bgColor = Color.Gray,
            icon = painterResource(R.drawable.ic_launcher_foreground),
            onClick = {},
            modifier = Modifier.weight(1f)
        )
    }
}