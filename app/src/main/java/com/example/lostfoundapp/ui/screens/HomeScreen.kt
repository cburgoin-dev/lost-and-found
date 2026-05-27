package com.example.lostfoundapp.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.R
import com.example.lostfoundapp.navigation.Routes
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

data class HomeItem(
    val title: String,
    val location: String,
    val time: String,
    val status: String,
    val statusBackground: Color,
    val statusTextColor: Color,
    val imageRes: Int
)

val homeItems = listOf(

    HomeItem(
        title = "AirPods Case",
        location = "Biblioteca Central",
        time = "10:15 AM",
        status = "Perdido",
        statusBackground = LostBadgeBackground,
        statusTextColor = LostBadgeText,
        imageRes = R.drawable.airpods_case
    ),

    HomeItem(
        title = "Credencial UABCS",
        location = "Edificio A",
        time = "09:30 AM",
        status = "Encontrado",
        statusBackground = FoundBadgeBackground,
        statusTextColor = FoundBadgeText,
        imageRes = R.drawable.student_id
    ),

    HomeItem(
        title = "Mochila Negra",
        location = "Gimnasio",
        time = "08:45 AM",
        status = "Perdido",
        statusBackground = LostBadgeBackground,
        statusTextColor = LostBadgeText,
        imageRes = R.drawable.backpack
    ),

    HomeItem(
        title = "Botella Térmica",
        location = "Cafetería",
        time = "11:20 AM",
        status = "Encontrado",
        statusBackground = FoundBadgeBackground,
        statusTextColor = FoundBadgeText,
        imageRes = R.drawable.water_bottle
    )
)

@Composable
fun HomeScreen(
    username: String = "Cristian",
    onLostClick: () -> Unit,
    onFoundClick: () -> Unit,

    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onItemClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit
) {

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

                    Icon(
                        imageVector = Icons.Outlined.NotificationsNone,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(26.dp)
                            .clickable {
                                onNotificationsClick()
                            }
                    )
                }

                Spacer(modifier = Modifier.height(42.dp))

                Text(
                    text = "Hola, $username",
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

                contentPadding = PaddingValues(bottom = 120.dp),

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
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

                items(homeItems) { item ->

                    ItemCard(
                        title = item.title,
                        location = item.location,
                        time = item.time,
                        status = item.status,
                        statusBackground = item.statusBackground,
                        statusTextColor = item.statusTextColor,
                        icon = painterResource(item.imageRes),
                        onClick = {
                            onItemClick()
                        }
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
                onHomeClick = onHomeClick,
                onSearchClick = onSearchClick,
                onNotificationsClick = onNotificationsClick,
                onProfileClick = onProfileClick
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){

    HomeScreen(
        username = "Cristian",
        onLostClick = {},
        onFoundClick = {},

        currentRoute = Routes.Home.route,
        onHomeClick = {},
        onSearchClick = {},
        onItemClick = {},
        onNotificationsClick = {},
        onProfileClick = {}
    )
}