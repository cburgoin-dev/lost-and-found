package com.example.lostfoundapp.ui.components.itemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.ui.theme.*

@Composable
fun StatusBadge(
    reportType: ReportType,
    fontSize: TextUnit = 14.sp,
    horizontalPadding: Dp = 14.dp,
    verticalPadding: Dp = 6.dp,
    cornerRadius: Dp = 100.dp
) {

    val backgroundColor =
        if(reportType == ReportType.LOST)
            LostBadgeBackground
        else
            FoundBadgeBackground

    val textColor =
        if(reportType == ReportType.LOST)
            LostBadgeText
        else
            FoundBadgeText

    val text =
        if(reportType == ReportType.LOST)
            "Perdido"
        else
            "Encontrado"

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )
    ) {

        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = fontSize
        )
    }
}