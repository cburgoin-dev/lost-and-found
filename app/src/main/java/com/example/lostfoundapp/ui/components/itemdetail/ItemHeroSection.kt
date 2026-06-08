package com.example.lostfoundapp.ui.components.itemdetail

import com.example.lostfoundapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.ui.components.BackButton
import com.example.lostfoundapp.ui.components.CircleIconButton

@Composable
fun ItemHeroSection(
    imageUrl: String?,
    postType: PostType,
    isMine: Boolean,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .background(Color.White)
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),

            placeholder = painterResource(R.drawable.airpods_case),
            error = painterResource(R.drawable.airpods_case)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 18.dp
                ),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                BackButton(
                    onClick = onBackClick,
                    backgroundColor = Color.Black.copy(alpha = 0.35f),
                    iconColor = Color.White
                )

                if(isMine) {

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    CircleIconButton(
                        icon = Icons.Outlined.Edit,
                        onClick = onEditClick
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    CircleIconButton(
                        icon = Icons.Outlined.Delete,
                        onClick = onDeleteClick
                    )
                }
            }

            StatusBadge(
                postType = postType,
                fontSize = 17.sp,
                horizontalPadding = 12.dp,
                verticalPadding = 6.dp,
                cornerRadius = 12.dp
            )
        }
    }
}