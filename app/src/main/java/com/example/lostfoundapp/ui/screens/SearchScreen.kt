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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.mock.categories
import com.example.lostfoundapp.data.mock.locations
import com.example.lostfoundapp.data.mock.mockPosts
import com.example.lostfoundapp.data.model.ItemPost

import com.example.lostfoundapp.navigation.Routes
import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.ItemCard
import com.example.lostfoundapp.ui.components.SelectionDialog
import com.example.lostfoundapp.ui.components.search.DateFilterRow
import com.example.lostfoundapp.ui.components.search.SearchFiltersSection
import com.example.lostfoundapp.ui.theme.HomeBodyBackground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.components.search.SearchInput

@Composable
fun SearchScreen(
    onItemClick: (ItemPost) -> Unit,

    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("")
    }

    var selectedLocation by remember {
        mutableStateOf("")
    }

    var selectedDateFilter by remember {
        mutableStateOf("Esta semana")
    }

    var showCategoryDialog by remember {
        mutableStateOf(false)
    }

    var showLocationDialog by remember {
        mutableStateOf(false)
    }

    val filteredPosts = mockPosts.filter { post ->

        val matchesCategory =
            selectedCategory.isEmpty() ||
            post.category == selectedCategory

        val matchesLocation =
            selectedLocation.isEmpty() ||
            post.location.contains(
                selectedLocation,
                ignoreCase = true
            )

        val matchesSearch =
            searchQuery.isEmpty() ||
            post.title.contains(
                searchQuery,
                ignoreCase = true
            ) ||
            post.location.contains(
                searchQuery,
                ignoreCase = true
            ) ||
            post.category.contains(
                searchQuery,
                ignoreCase = true
            )

        val matchesDate = true

        matchesCategory &&
        matchesLocation &&
        matchesSearch &&
        matchesDate
    }

    val keyboardController =
        LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeHeaderBlue)
        ) {

            Spacer(modifier = Modifier.height(56.dp))

            Text(
                text = "Buscar objetos",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {

                SearchInput(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

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

                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    )
                ) {

                    SearchFiltersSection(
                        selectedCategory = selectedCategory,
                        selectedLocation = selectedLocation,

                        onCategoryClick = {
                            showCategoryDialog = true
                        },

                        onLocationClick = {
                            showLocationDialog = true
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DateFilterRow(
                        selectedFilter = selectedDateFilter,
                        onFilterSelected = {
                            selectedDateFilter = it
                        }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    val resultsText =
                        when(filteredPosts.size) {
                            0 -> "0 resultados"
                            1 -> "1 resultado"
                            else -> "${filteredPosts.size} resultados"
                        }

                    Text(
                        text = resultsText,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (filteredPosts.isEmpty()) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(top = 56.dp),

                            contentAlignment = Alignment.TopCenter
                        ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(48.dp)
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                Text(
                                    text = "No se encontraron resultados",
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Prueba con otros filtros",
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

                            items(filteredPosts) { item ->

                                ItemCard(
                                    itemPost = item,
                                    onClick = {
                                        keyboardController?.hide()
                                        onItemClick(item)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        if(showCategoryDialog) {

            SelectionDialog(
                title = "Seleccionar categoría",
                options = listOf(
                    "Limpiar selección"
                ) + categories,

                onDismiss = {
                    showCategoryDialog = false
                },

                onOptionSelected = {

                    selectedCategory =
                        if(it == "Limpiar selección")
                            ""
                        else
                            it
                    showCategoryDialog = false
                }
            )
        }

        if(showLocationDialog) {

            SelectionDialog(
                title = "Seleccionar ubicación",
                options = listOf(
                    "Limpiar selección"
                ) + locations,

                onDismiss = {
                    showLocationDialog = false
                },

                onOptionSelected = {

                    selectedLocation =
                        if(it == "Limpiar selección")
                            ""
                        else
                            it
                    showLocationDialog = false
                }
            )
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

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {

    SearchScreen(
        currentRoute = Routes.Search.route,
        onHomeClick = {},
        onSearchClick = {},
        onNotificationsClick = {},
        onProfileClick = {},
        onItemClick = {}
    )
}