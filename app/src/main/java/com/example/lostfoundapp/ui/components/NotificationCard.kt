package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
fun NotificationCard(

    titleTop: String,
    title: String,
    description: String,
    time: String,

    borderColor: Color,
    iconBackground: Color,

    showBlueDot: Boolean,

    icon: Painter,

    onClick: () -> Unit

) {

    Box {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Color.White)
                .border(
                    width = 4.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(18.dp)
                )
                .clickable {
                    onClick()
                }
                .padding(16.dp),

            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(82.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(iconBackground),

                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titleTop,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = description,
                    fontSize = 20.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = time,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }

        if (showBlueDot) {

            Box(
                modifier = Modifier
                    .padding(14.dp)
                    .align(Alignment.TopEnd)
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF64B5F6))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationCardPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(12.dp),

        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        NotificationCard(
            titleTop = "Coincidencia",
            title = "Encontramos una posible coincidencia",
            description = "Un usuario reportó una mochila similar a la tuya.",
            time = "Hace 5 min",

            borderColor = Color(0xFF64B5F6),
            iconBackground = Color(0xFFE3F2FD),

            showBlueDot = true,

            icon = painterResource(R.drawable.ic_launcher_foreground),

            onClick = {}
        )

        NotificationCard(
            titleTop = "Solicitud de reclamación",
            title = "Alguien quiere reclamar tu objeto",
            description = "Revisa la solicitud enviada para confirmar la propiedad.",
            time = "",

            borderColor = Color(0xFFFF7043),
            iconBackground = Color(0xFFFFE0D6),

            showBlueDot = false,

            icon = painterResource(R.drawable.ic_launcher_foreground),

            onClick = {}
        )
    }
}