package com.example.lostfoundapp.ui.components.itemdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun ItemInfoSection(
    title: String,
    location: String,
    date: String,
    description: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {

        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = HomeHeaderBlue
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = location,
                color = DetailSecondaryText,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = null,
                tint = HomeHeaderBlue
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = date,
                color = DetailSecondaryText,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = description,

            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                lineHeight = 26.sp
            )
        )
    }
}