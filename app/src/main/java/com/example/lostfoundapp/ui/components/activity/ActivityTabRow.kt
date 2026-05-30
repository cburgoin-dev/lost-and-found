package com.example.lostfoundapp.ui.components.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lostfoundapp.ui.theme.HomeBodyBackground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun ActivityTabRow(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(4.dp)
        ) {

            Box(
                modifier = Modifier.weight(1f)
            ) {

                ActivityTabItem(
                    text = "Solicitudes",
                    selected = selectedTab == "Solicitudes",
                    onClick = {
                        onTabSelected("Solicitudes")
                    }
                )
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {

                ActivityTabItem(
                    text = "Notificaciones",
                    selected = selectedTab == "Notificaciones",
                    onClick = {
                        onTabSelected("Notificaciones")
                    }
                )
            }
        }
    }
}