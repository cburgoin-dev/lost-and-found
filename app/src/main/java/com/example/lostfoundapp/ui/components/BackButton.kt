package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.example.lostfoundapp.ui.theme.BackgroundGray

@Composable
fun BackButton(
    onClick: () -> Unit,
    backgroundColor: Color = BackgroundGray,
    iconColor: Color = Color.Black,
    hasBackground: Boolean = true
) {

    Box(
        modifier =
            if (hasBackground) {

                Modifier
                    .size(42.dp)
                    .background(
                        backgroundColor,
                        CircleShape
                    )
                    .clickable {
                        onClick()
                    }
            } else {

                Modifier
                    .size(42.dp)
                    .clickable {
                        onClick()
                    }
            },

        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = null,
            tint = iconColor
        )
    }
}