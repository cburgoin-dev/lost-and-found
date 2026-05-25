package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeActionCard(
    title: String,
    icon: ImageVector,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .height(150.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(22.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(20.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = textColor,

                    modifier = Modifier.size(42.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = title,
                    color = textColor,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}