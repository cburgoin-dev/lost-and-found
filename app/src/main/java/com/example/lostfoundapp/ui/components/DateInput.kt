package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun DateInput(
    text: String,
    placeholder: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                1.dp,
                BorderGray,
                RoundedCornerShape(16.dp)
            )
            .background(
                Color.White,
                RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text.ifEmpty { placeholder },
            color = TextGray,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = null,
            tint = TextGray
        )
    }
}