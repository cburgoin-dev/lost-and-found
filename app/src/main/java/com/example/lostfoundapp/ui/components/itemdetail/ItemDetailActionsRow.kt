package com.example.lostfoundapp.ui.components.itemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.theme.FoundActionCardForeground

@Composable
fun ItemDetailActionsRow(
    onSaveClick: () -> Unit,
    onShareClick: () -> Unit,
    onReportClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        // SI ALCANZA EL TIEMPO Y VALE LA PENA IMPLEMENTAR ESTE BOTÓN

        /*
        BottomActionItem(
            modifier = Modifier.weight(1f),
            icon = {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Outlined.Share,
                    contentDescription = null,
                    tint = FoundActionCardForeground
                )
            },
            text = "Compartir",
            onClick = onShareClick
        )
         */

        BottomActionItem(
            modifier = Modifier.weight(1f),
            icon = {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Outlined.BookmarkBorder,
                    contentDescription = null,
                    tint = FoundActionCardForeground
                )
            },
            text = "Guardar publicación",
            onClick = onSaveClick
        )

        BottomActionItem(
            modifier = Modifier.weight(1f),
            icon = {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Outlined.Flag,
                    contentDescription = null,
                    tint = FoundActionCardForeground
                )
            },
            text = "Reportar publicación",
            onClick = onReportClick
        )
    }
}

@Composable
private fun BottomActionItem(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    text: String,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier.clickable {
            onClick()
        },

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        icon()

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text,
            color = FoundActionCardForeground.copy(alpha = 0.92f),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}