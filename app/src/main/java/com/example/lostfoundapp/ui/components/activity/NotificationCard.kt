package com.example.lostfoundapp.ui.components.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.NotificationType
import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.GoldAccent
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun NotificationCard(
    notification: Notification,
    onClick: () -> Unit
) {

    val icon =
        when(notification.type) {

            NotificationType.REQUEST_APPROVED ->
                Icons.Outlined.CheckCircle

            NotificationType.REQUEST_REJECTED ->
                Icons.Outlined.Cancel

            NotificationType.MATCH_FOUND ->
                Icons.Outlined.Search

            NotificationType.SYSTEM ->
                Icons.Outlined.Notifications
        }

    val iconColor =
        when(notification.type) {

            NotificationType.REQUEST_APPROVED ->
                Color(0xFF2E7D32)

            NotificationType.REQUEST_REJECTED ->
                Color(0xFFC62828)

            NotificationType.MATCH_FOUND ->
                Color(0xFF7B1FA2)

            NotificationType.SYSTEM ->
                HomeHeaderBlue
        }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(22.dp)
            )
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    Text(
                        text = notification.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

                if (!notification.isRead) {

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(
                                Color(0xFFE53935)
                            )
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = notification.description,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = DetailSecondaryText
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = notification.createdAt,
                fontSize = 13.sp,
                color = DetailSecondaryText
            )
        }
    }
}
