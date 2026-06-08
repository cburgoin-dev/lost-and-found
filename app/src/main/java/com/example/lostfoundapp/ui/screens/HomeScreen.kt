package com.example.lostfoundapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.R
import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.ItemCard
import com.example.lostfoundapp.ui.components.HomeActionCard
import com.example.lostfoundapp.ui.theme.BackgroundGray
import com.example.lostfoundapp.ui.theme.FoundActionCardBackground
import com.example.lostfoundapp.ui.theme.FoundActionCardForeground
import com.example.lostfoundapp.ui.theme.FoundBadgeBackground
import com.example.lostfoundapp.ui.theme.FoundBadgeText
import com.example.lostfoundapp.ui.theme.HomeBodyBackground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.LostActionCardBackground
import com.example.lostfoundapp.ui.theme.LostActionCardForeground
import com.example.lostfoundapp.ui.theme.LostBadgeBackground
import com.example.lostfoundapp.ui.theme.LostBadgeText
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.ui.viewmodel.PostsViewModel
import com.example.lostfoundapp.ui.viewmodel.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    userViewModel: UserViewModel,
    postsViewModel: PostsViewModel,

    hasUnreadActivity: Boolean,
    onLostClick: () -> Unit,
    onFoundClick: () -> Unit,
    onItemClick: (ItemPost) -> Unit,

    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    val posts =
        postsViewModel.posts

    val firstName =
        userViewModel.user
            ?.name
            ?.substringBefore(" ")
            ?: ""

    val showMessage =
        postsViewModel.showPostCreatedMessage

    LaunchedEffect(Unit) {
        postsViewModel.loadPosts()
    }

    LaunchedEffect(showMessage) {

        if(showMessage) {

            delay(3000)

            postsViewModel.clearPostCreatedMessage()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(
                    color = HomeHeaderBlue,
                    shape = RoundedCornerShape(
                        bottomStart = 36.dp,
                        bottomEnd = 36.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 32.dp
                    )
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(R.drawable.uabcs_logo),
                            contentDescription = null,
                            modifier = Modifier.size(42.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(
                            verticalArrangement = Arrangement.spacedBy((-2).dp)
                        ) {

                            Text(
                                text = "Lost & Found",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = "UABCS",
                                color = Color.White.copy(alpha = 0.75f),
                                fontSize = 15.sp
                            )
                        }
                    }

                    Box {

                        Icon(
                            imageVector = Icons.Outlined.NotificationsNone,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {
                                    onActivityClick()
                                }
                        )

                        if (hasUnreadActivity) {

                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .align(Alignment.TopEnd)
                                    .clip(CircleShape)
                                    .background(
                                        Color(0xFFD32F2F)
                                    )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(42.dp))

                Text(
                    text = "Hola, $firstName",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "¿Perdiste o encontraste algo hoy?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White.copy(alpha = 0.78f)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp)
                .background(
                    color = HomeBodyBackground,
                    shape = RoundedCornerShape(
                        topStart = 34.dp,
                        topEnd = 34.dp
                    )
                )
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 26.dp
                    ),

                contentPadding = PaddingValues(bottom = 144.dp),

                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                item {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        HomeActionCard(
                            title = "Perdí algo",
                            icon = Icons.Outlined.SearchOff,
                            backgroundColor = LostActionCardBackground,
                            textColor = LostActionCardForeground,
                            onClick = {
                                onLostClick()
                            },
                            modifier = Modifier.weight(1f)
                        )

                        HomeActionCard(
                            title = "Encontré algo",
                            icon = Icons.Outlined.NearMe,
                            backgroundColor = FoundActionCardBackground,
                            textColor = FoundActionCardForeground,
                            onClick = {
                                onFoundClick()
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {

                    Text(
                        text = "Recientes en el campus",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
                if(posts.isEmpty()) {

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.SearchOff,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(48.dp)
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = "No hay publicaciones todavía",
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = "Cuando alguien reporte un objeto aparecerá aquí",
                                color = Color.Gray
                            )
                        }
                    }

                } else {

                    items(posts) { item ->

                        ItemCard(
                            title = item.title,
                            location = item.location,
                            time = item.date,
                            status =
                                if(item.reportType == ReportType.LOST)
                                    "Perdido"
                                else
                                    "Encontrado",
                            statusBackground =
                                if(item.reportType == ReportType.LOST)
                                    LostBadgeBackground
                                else
                                    FoundBadgeBackground,
                            statusTextColor =
                                if(item.reportType == ReportType.LOST)
                                    LostBadgeText
                                else
                                    FoundBadgeText,
                            imageUrl = item.imageUrl,
                            onClick = {
                                onItemClick(item)
                            }
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = showMessage,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start =6.dp,
                        end = 6.dp,
                        bottom = 130.dp


                    )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0xFF4CAF50),
                            RoundedCornerShape(12.dp)
                        )
                ) {

                    BasicText(
                        text = "Publicación creada con éxito",
                        style = TextStyle(color = Color.White),
                        modifier = Modifier.padding(
                            horizontal = 26.dp,
                            vertical = 8.dp
                        )
                    )
                }
            }
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            AppBottomBar(
                currentRoute = currentRoute,
                hasUnreadActivity = hasUnreadActivity,
                onHomeClick = onHomeClick,
                onSearchClick = onSearchClick,
                onActivityClick = onActivityClick,
                onProfileClick = onProfileClick
            )
        }
    }
}