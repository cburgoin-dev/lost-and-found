package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.AccentBlue

@Composable

fun AppBottomBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .navigationBarsPadding()
            .background(Color.White)
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            ),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomBarItem(
            label = "Inicio",
            icon = Icons.Outlined.Home,
            selected = true
        )

        BottomBarItem(
            label = "Buscar",
            icon = Icons.Outlined.Search,
            selected = false
        )

        BottomBarItem(
            label = "Alertas",
            icon = Icons.Outlined.Notifications,
            selected = false
        )

        BottomBarItem(
            label = "Perfil",
            icon = Icons.Outlined.Person2,
            selected = false
        )
    }
}