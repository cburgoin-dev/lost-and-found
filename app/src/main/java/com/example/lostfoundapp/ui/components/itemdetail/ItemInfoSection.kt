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
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun ItemInfoSection(
    title: String,
    location: String,
    date: String,
    description: String,
    reportType: ReportType
) {

    val locationLabel =
        if(reportType == ReportType.FOUND)
            "Encontrado en"
        else
            "Última ubicación conocida"

    val dateLabel =
        if(reportType == ReportType.FOUND)
            "Fecha de hallazgo"
        else
            "Última vez visto"

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

        Column {

            Text(
                text = locationLabel,
                color = HomeHeaderBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(2.dp))

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
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column {

            Text(
                text = dateLabel,
                color = HomeHeaderBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(2.dp))

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
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
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