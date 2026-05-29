package com.example.lostfoundapp.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun DateFilterRow(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {

    val filters = listOf(
        "Hoy",
        "Esta semana",
        "Este mes",
        "Todo el tiempo"
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        items(filters) { filter ->

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onFilterSelected(filter)
                    }
            ) {

                Text(
                    text = filter,
                    fontWeight =
                        if(filter == selectedFilter)
                            FontWeight.SemiBold
                        else
                            FontWeight.Normal,

                    color =
                        if(filter == selectedFilter)
                            HomeHeaderBlue
                        else
                            Color.Gray,

                    modifier = Modifier.drawBehind {

                        if(filter == selectedFilter) {

                            drawLine(
                                color = HomeHeaderBlue,
                                start = Offset(0f, size.height + 8.dp.toPx()),
                                end = Offset(size.width, size.height + 8.dp.toPx()),
                                strokeWidth = 3.dp.toPx()
                            )
                        }
                    }
                )
            }
        }
    }
}