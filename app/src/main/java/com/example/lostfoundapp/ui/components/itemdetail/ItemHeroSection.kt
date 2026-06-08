package com.example.lostfoundapp.ui.components.itemdetail

import com.example.lostfoundapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.ui.components.BackButton
import com.example.lostfoundapp.ui.theme.*

@Composable
fun ItemHeroSection(
    imageUrl: String?,
    reportType: ReportType,
    onBackClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .background(Color.White)
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),

            placeholder = painterResource(R.drawable.airpods_case),
            error = painterResource(R.drawable.airpods_case)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 18.dp
                ),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BackButton(
                onClick = onBackClick,
                backgroundColor = Color.Black.copy(alpha = 0.35f),
                iconColor = Color.White
            )

            StatusBadge(
                reportType = reportType,
                fontSize = 17.sp,
                horizontalPadding = 12.dp,
                verticalPadding = 6.dp,
                cornerRadius = 12.dp
            )
        }
    }
}