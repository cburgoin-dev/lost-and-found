package com.example.lostfoundapp.ui.components.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.ui.theme.ApprovedBadgeBackground
import com.example.lostfoundapp.ui.theme.ApprovedBadgeText
import com.example.lostfoundapp.ui.theme.PendingBadgeBackground
import com.example.lostfoundapp.ui.theme.PendingBadgeText
import com.example.lostfoundapp.ui.theme.ReadBadgeBackground
import com.example.lostfoundapp.ui.theme.ReadBadgeText
import com.example.lostfoundapp.ui.theme.RejectedBadgeBackground
import com.example.lostfoundapp.ui.theme.RejectedBadgeText

@Composable
fun RequestStatusBadge(
    status: RequestStatus
) {

    val backgroundColor =
        when(status) {
            RequestStatus.PENDING -> PendingBadgeBackground
            RequestStatus.APPROVED -> ApprovedBadgeBackground
            RequestStatus.REJECTED -> RejectedBadgeBackground
        }

    val textColor =
        when(status) {
            RequestStatus.PENDING -> PendingBadgeText
            RequestStatus.APPROVED -> ApprovedBadgeText
            RequestStatus.REJECTED -> RejectedBadgeText
        }

    val text =
        when(status) {
            RequestStatus.PENDING -> "Pendiente"
            RequestStatus.APPROVED -> "Aprobada"
            RequestStatus.REJECTED -> "Rechazada"
        }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(backgroundColor)
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )
    ) {

        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}