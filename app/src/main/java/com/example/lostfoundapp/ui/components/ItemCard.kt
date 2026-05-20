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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.R


@Composable
fun ItemCard(
    descriptionTop: String,
    title: String,
    descriptionBottom: String,
    status: String,
    statusColor: Color,
    icon: Painter,
    onClick: () -> Unit,

) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp)
            .clickable{
                onClick()
            },

        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .height(55.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = descriptionTop,
                color = Color.Gray,
                fontSize = 14.sp
            )

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = descriptionBottom,
                color = Color.Gray,
                fontSize = 16.sp
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(statusColor)
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {

            Text(
                text = status,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

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
            descriptionTop = "top",
            title= "mid",
            descriptionBottom="bot",
            status = "Lost",
            statusColor = Color(0xFFFFC300),
            icon= painterResource(R.drawable.ic_launcher_foreground),
            onClick = {}
        )

        ItemCard(
            descriptionTop = "horse",
            title= "race",
            descriptionBottom="test",
            status = "Found",
            statusColor = Color(0xFF2CBEC0),
            icon=painterResource(R.drawable.ic_launcher_foreground),
            onClick = {}
        )
    }
}