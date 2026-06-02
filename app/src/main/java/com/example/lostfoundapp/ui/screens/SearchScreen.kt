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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lostfoundapp.data.mock.categories
import com.example.lostfoundapp.data.mock.locations
import com.example.lostfoundapp.data.mock.mockPosts
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType

import com.example.lostfoundapp.navigation.Routes
import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.ItemCard
import com.example.lostfoundapp.ui.components.SelectionDialog
import com.example.lostfoundapp.ui.components.search.DateFilterRow
import com.example.lostfoundapp.ui.components.search.SearchFiltersSection
import com.example.lostfoundapp.ui.theme.HomeBodyBackground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.components.search.SearchInput

import com.example.lostfoundapp.ui.viewmodel.SearchViewModel

import com.example.lostfoundapp.ui.theme.FoundBadgeBackground
import com.example.lostfoundapp.ui.theme.FoundBadgeText
import com.example.lostfoundapp.ui.theme.LostBadgeBackground
import com.example.lostfoundapp.ui.theme.LostBadgeText

import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.remote.RetrofitInstance
import com.example.lostfoundapp.data.toItemPost
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext


@Composable
fun SearchScreen(
    onItemClick: (ItemPost) -> Unit,

    currentRoute: String?,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    var posts by remember {
        mutableStateOf<List<ItemPost>>(emptyList())
    }

    val context = LocalContext.current

    val sessionManager = SessionManager(context)

    val token = sessionManager.getToken() ?: ""
    val viewModel: SearchViewModel = viewModel()


    LaunchedEffect(viewModel.selectedCategoryId,
            viewModel.selectedLocationId,
            viewModel.selectedDateFilter) {

        try {

            val response =
                RetrofitInstance.api.getPosts(
                    token = "Bearer $token",

                    categoryId =
                        viewModel.selectedCategoryId,

                    locationId =
                        viewModel.selectedLocationId,

                    time =
                        viewModel.selectedDateFilter.lowercase()
                )

            if(response.isSuccessful) {

                posts =
                    response.body()
                        ?.data
                        ?.map { it.toItemPost() }
                        ?: emptyList()
            }

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    val filteredPosts = posts.filter { post ->

        val matchesCategory =
            viewModel.selectedCategory.isEmpty() ||
            post.category == viewModel.selectedCategory

        val matchesLocation =
            viewModel.selectedLocation.isEmpty() ||
            post.location.contains(
                viewModel.selectedLocation,
                ignoreCase = true
            )

        val matchesSearch =
            viewModel.searchQuery.isEmpty() ||
            post.title.contains(
                viewModel.searchQuery,
                ignoreCase = true
            ) ||
            post.location.contains(
                viewModel.searchQuery,
                ignoreCase = true
            ) ||
            post.category.contains(
                viewModel.searchQuery,
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
                    value = viewModel.searchQuery,
                    onValueChange = {
                        viewModel.updateSearchQuery(it)
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
                        selectedCategory = viewModel.selectedCategory,
                        selectedLocation = viewModel.selectedLocation,

                        onCategoryClick = {
                            viewModel.showCategoryDialog()
                        },

                        onLocationClick = {
                            viewModel.showLocationDialog()
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DateFilterRow(
                        selectedFilter = viewModel.selectedDateFilter,
                        onFilterSelected = {
                            viewModel.updateDateFilter(it)
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

        if(viewModel.showCategoryDialog) {

            SelectionDialog(
                title = "Seleccionar categoría",
                options = categories.map{it.name},
                selectedOption = viewModel.selectedCategory,

                onDismiss = {
                    viewModel.hideCategoryDialog()
                },

                onOptionSelected = { optionSelected ->

                    val selectedCategory = categories.find {
                        it.name == optionSelected
                    }

                    viewModel.updateCategory(
                        id = selectedCategory?.id,
                        name = selectedCategory?.name ?: ""
                    )

                    viewModel.hideCategoryDialog()
                }
            )
        }

        if(viewModel.showLocationDialog) {

            SelectionDialog(
                title = "Seleccionar ubicación",
                options = locations.map{it.name},
                selectedOption = viewModel.selectedLocation,

                onDismiss = {
                    viewModel.hideLocationDialog()
                },

                onOptionSelected = { optionSelected ->

                    val selectedLocation = locations.find {
                        it.name == optionSelected
                    }

                    viewModel.updateLocation(
                        id = selectedLocation?.id,
                        name = selectedLocation?.name ?: ""
                    )

                    viewModel.hideLocationDialog()
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
                onActivityClick = onActivityClick,
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
        onActivityClick = {},
        onProfileClick = {},
        onItemClick = {}
    )
}