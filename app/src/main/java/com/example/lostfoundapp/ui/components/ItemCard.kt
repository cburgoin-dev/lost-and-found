package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.R
import com.example.lostfoundapp.ui.theme.FoundBadgeBackground
import com.example.lostfoundapp.ui.theme.FoundBadgeText
import com.example.lostfoundapp.ui.theme.LostBadgeBackground
import com.example.lostfoundapp.ui.theme.LostBadgeText


@Composable
fun ItemCard(
    title: String,
    location: String,
    time: String,
    status: String,
    statusBackground: Color,
    statusTextColor: Color,
    imageUrl: String?,
    //icon: Painter,
    onClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(22.dp),
                clip = false
            )
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .clickable{
                onClick()
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFF3F4F6)),
                contentAlignment = Alignment.Center
            ) {
                /*
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.height(52.dp)
                )
                */

                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.airpods_case),
                    error = painterResource(R.drawable.airpods_case),
                    modifier = Modifier.size(72.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = location,
                    color = Color(0xFF707070),
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = time,
                    color = Color(0xFF9A9A9A),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 14.dp,
                    bottom = 14.dp
                )
                .clip(RoundedCornerShape(100.dp))
                .background(statusBackground)
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                )
        ) {

            Text(
                text = status,
                color = statusTextColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        ItemCard(
            title = "AirPods Case",
            location = "Biblioteca Central",
            time = "10:15 AM",
            status = "Perdido",
            statusBackground = LostBadgeBackground,
            statusTextColor = LostBadgeText,
            icon = painterResource(R.drawable.ic_launcher_foreground),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        ItemCard(
            title = "Credencial UABCS",
            location = "Edificio A",
            time = "09:30 AM",
            status = "Encontrado",
            statusBackground = FoundBadgeBackground,
            statusTextColor = FoundBadgeText,
            icon = painterResource(R.drawable.ic_launcher_foreground),
            onClick = {}
        )
    }
}*/