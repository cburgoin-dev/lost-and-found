package com.example.lostfoundapp.ui.components.profile

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun ProfileHeader(
    userName: String,
    email: String,
    phone: String,
    profileImageUrl: String?
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(140.dp)
                .background(
                    Color.White,
                    CircleShape
                )
                .padding(4.dp)
        ) {

            if (!profileImageUrl.isNullOrBlank()) {

                AsyncImage(
                    model = profileImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(132.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

            } else {

                Box(
                    modifier = Modifier
                        .size(132.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF1F1F1)),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        tint = TextGray,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }

        Text(
            text = userName,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = DetailSecondaryText
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = email,
                    fontSize = 16.sp,
                    color = DetailSecondaryText
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = DetailSecondaryText
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = phone,
                    fontSize = 16.sp,
                    color = DetailSecondaryText
                )
            }
        }
    }
}