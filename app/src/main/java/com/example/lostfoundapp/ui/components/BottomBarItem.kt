package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun BottomBarItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showBadge: Boolean = false
) {

    Column(
        modifier = modifier.clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .width(76.dp)
                .height(4.dp)
                .background(
                    color = if (selected) {
                        HomeHeaderBlue
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(100.dp)
                )
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if(selected) HomeHeaderBlue else TextGray,
                modifier = Modifier.size(28.dp)
            )

            if (showBadge) {

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(Color(0xFFD32F2F))
                )
            }
        }

        Text(
            text = label,
            color = if(selected) HomeHeaderBlue else TextGray,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}