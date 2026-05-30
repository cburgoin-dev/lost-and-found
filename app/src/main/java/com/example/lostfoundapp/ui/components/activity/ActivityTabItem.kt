package com.example.lostfoundapp.ui.components.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.ui.theme.HomeBodyBackground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun ActivityTabItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color =
                    if (selected)
                        HomeHeaderBlue
                    else
                        Color.Transparent,

                shape = RoundedCornerShape(14.dp)
            )
            .clickable {
                onClick()
            }
            .height(48.dp),

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            fontSize = 16.sp,

            color =
                if (selected)
                    Color.White
                else
                    HomeHeaderBlue,

            fontWeight =
                if (selected)
                    FontWeight.Bold
                else
                    FontWeight.Medium
        )
    }
}