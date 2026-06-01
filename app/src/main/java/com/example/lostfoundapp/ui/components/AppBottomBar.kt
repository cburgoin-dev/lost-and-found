package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.example.lostfoundapp.navigation.Routes
import com.example.lostfoundapp.ui.theme.BottomBarDivider

@Composable

fun AppBottomBar(
    currentRoute: String?,
    hasUnreadActivity: Boolean = false,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .navigationBarsPadding()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(
                    bottom = 5.dp
                ),

            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarItem(
                label = "Inicio",
                icon = Icons.Filled.Home,
                selected = currentRoute == Routes.Home.route,
                onClick = {
                    onHomeClick()
                },
                modifier = Modifier.weight(1f)
            )

            BottomBarItem(
                label = "Buscar",
                icon = Icons.Outlined.Search,
                selected = currentRoute == Routes.Search.route,
                onClick = {
                    onSearchClick()
                },
                modifier = Modifier.weight(1f)
            )

            BottomBarItem(
                label = "Actividad",
                icon = Icons.Filled.Notifications,
                selected = currentRoute == Routes.Activity.route,
                showBadge = hasUnreadActivity,
                onClick = {
                    onActivityClick()
                },
                modifier = Modifier.weight(1f)
            )

            BottomBarItem(
                label = "Perfil",
                icon = Icons.Filled.Person,
                selected = currentRoute == Routes.Profile.route,
                onClick = {
                    onProfileClick()
                },
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}