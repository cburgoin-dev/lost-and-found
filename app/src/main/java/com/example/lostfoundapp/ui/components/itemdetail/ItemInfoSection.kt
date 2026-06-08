package com.example.lostfoundapp.ui.components.itemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun ItemInfoSection(
    itemPost: ItemPost,
) {

    val locationLabel =
        if(itemPost.postType == PostType.FOUND)
            "Encontrado en"
        else
            "Última ubicación conocida"

    val dateLabel =
        if(itemPost.postType == PostType.FOUND)
            "Fecha de hallazgo"
        else
            "Última vez visto"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {


        Text(
            text = itemPost.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column {

            Text(
                text = locationLabel,
                color = HomeHeaderBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        HomeHeaderBlue.copy(alpha = 0.05f),
                        RoundedCornerShape(14.dp)
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 12.dp
                    ),

                        verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = HomeHeaderBlue
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = itemPost.location,
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {

            Text(
                text = dateLabel,
                color = HomeHeaderBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        HomeHeaderBlue.copy(alpha = 0.05f),
                        RoundedCornerShape(14.dp)
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 12.dp
                    ),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    tint = HomeHeaderBlue
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = itemPost.date,
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = itemPost.description,

            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                lineHeight = 26.sp
            )
        )
    }
}