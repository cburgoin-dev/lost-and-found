package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.lostfoundapp.data.mock.mockNotifications
import com.example.lostfoundapp.data.model.Notification
import com.example.lostfoundapp.data.model.NotificationType
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.activity.ActivityTabRow
import com.example.lostfoundapp.ui.components.activity.NotificationCard
import com.example.lostfoundapp.ui.components.activity.RequestCard
import com.example.lostfoundapp.ui.theme.HomeBodyBackground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.viewmodel.ActivityViewModel
import com.example.lostfoundapp.ui.viewmodel.NotificationsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import com.example.lostfoundapp.ui.viewmodel.RequestsViewModel

@Composable
fun ActivityScreen(
    currentRoute: String?,
    hasUnreadActivity: Boolean,
    activityViewModel: ActivityViewModel = viewModel(),
    notificationsViewModel: NotificationsViewModel,
    requestsViewModel: RequestsViewModel,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit,
    onRequestClick: (Request) -> Unit,
    onNotificationClick: (Notification) -> Unit
) {
    val requests =
        requestsViewModel.requests

    LaunchedEffect(requests) {

        activityViewModel.updatePendingRequests(
            requests
        )
    }

    LaunchedEffect(
        notificationsViewModel.notifications
    ) {

        activityViewModel.updateUnreadNotifications(
            notificationsViewModel.notifications
        )
    }

    DisposableEffect(Unit) {

        onDispose {

            notificationsViewModel
                .markAllNotificationsAsRead()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeHeaderBlue)
        ) {

            Spacer(modifier = Modifier.height(56.dp))

            Text(
                text = "Actividad",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ActivityTabRow(
                selectedTab = activityViewModel.selectedTab,
                onTabSelected = { newTab ->

                    if (
                        activityViewModel.selectedTab == "Notificaciones" && newTab != "Notificaciones"
                    ) {

                        notificationsViewModel.markAllNotificationsAsRead()
                    }

                    activityViewModel.selectTab(newTab)
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp
                        )
                    )
                    .background(HomeBodyBackground)
            ) {

                Column (
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    )
                ) {

                    if(activityViewModel.selectedTab == "Solicitudes") {

                        if(requests.isEmpty()) {

                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "No tienes solicitudes",
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    Spacer(
                                        modifier = Modifier.height(4.dp)
                                    )

                                    Text(
                                        text = "Las solicitudes aparecerán aquí",
                                        color = Color.Gray
                                    )
                                }
                            }

                        } else {

                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(bottom = 120.dp),
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {

                                items(requests) { request ->

                                    RequestCard(
                                        request = request,
                                        onClick = {
                                            onRequestClick(request)
                                        }
                                    )
                                }
                            }
                        }

                    } else {

                        if(!notificationsViewModel.hasNotifications) {

                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "No tienes notificaciones",
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    Spacer(
                                        modifier = Modifier.height(4.dp)
                                    )

                                    Text(
                                        text = "Las nuevas notificaciones aparecerán aquí",
                                        color = Color.Gray
                                    )
                                }
                            }

                        } else {

                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(bottom = 120.dp),
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {

                                items(notificationsViewModel.notifications) { notification ->

                                    NotificationCard(
                                        notification = notification,
                                        onClick = {

                                            if(

                                                notification.type == NotificationType.REQUEST_APPROVED ||
                                                notification.type == NotificationType.MATCH_FOUND
                                            ) {

                                                notificationsViewModel
                                                    .markNotificationAsRead(
                                                        notification.id
                                                    )
                                            }

                                            onNotificationClick(notification)
                                        }
                                    )
                                }
                            }
                        }
                    }
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
