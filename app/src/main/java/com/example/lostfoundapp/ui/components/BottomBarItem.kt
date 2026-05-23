package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.AccentBlue
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun BottomBarItem(
    label: String,
    icon: ImageVector,
    selected: Boolean
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if(selected) AccentBlue else TextGray,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = label,
            color = if(selected) AccentBlue else TextGray,
            fontSize = 12.sp
        )
    }
}