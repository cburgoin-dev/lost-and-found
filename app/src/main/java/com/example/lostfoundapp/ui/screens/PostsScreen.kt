package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostsScreenType
import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.ui.components.BackButton
import com.example.lostfoundapp.ui.components.ItemCard
import com.example.lostfoundapp.ui.theme.FoundBadgeBackground
import com.example.lostfoundapp.ui.theme.FoundBadgeText
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.LostBadgeBackground
import com.example.lostfoundapp.ui.theme.LostBadgeText

@Composable
fun PostsScreen(
    title: String,
    posts: List<ItemPost>,
    screenType: PostsScreenType,
    onBackClick: () -> Unit,
    onItemClick: (ItemPost) -> Unit
) {

    val emptyIcon =
        if(screenType == PostsScreenType.USER_POSTS)
            Icons.Outlined.Description
        else
            Icons.Outlined.Bookmark

    val emptyTitle =
        if(screenType == PostsScreenType.USER_POSTS)
            "Aún no tienes publicaciones"
        else
            "No tienes publicaciones guardadas"

    val emptyDescription =
        if(screenType == PostsScreenType.USER_POSTS)
            "Tus publicaciones aparecerán aquí"
        else
            "Las publicaciones que guardes aparecerán aquí"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(
                WindowInsets.safeDrawing
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .background(HomeHeaderBlue)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                BackButton(
                    onClick = onBackClick,
                    hasBackground = false,
                    iconColor = Color.White
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.width(42.dp)
                )
            }
        }

        if (posts.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = emptyIcon,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = emptyTitle,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = emptyDescription,
                        color = Color.Gray
                    )
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = 24.dp,
                    vertical = 24.dp
                ),

                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                items(posts) { item ->

                    ItemCard(
                        title = item.title,
                        location = item.location,
                        time = item.createdAt,
                        status =
                            if(item.postType == PostType.LOST)
                                "Perdido"
                            else
                                "Encontrado",

                        statusBackground =
                            if(item.postType == PostType.LOST)
                                LostBadgeBackground
                            else
                                FoundBadgeBackground,

                        statusTextColor =
                            if(item.postType == PostType.LOST)
                                LostBadgeText
                            else
                                FoundBadgeText,

                        imageRes = item.imageRes,
                        imageUrl = item.imageUrl,

                        onClick = {
                            onItemClick(item)
                        }
                    )
                }
            }
        }
    }
}