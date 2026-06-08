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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostType

import com.example.lostfoundapp.ui.components.AppBottomBar
import com.example.lostfoundapp.ui.components.ItemCard
import com.example.lostfoundapp.ui.components.SelectionDialog
import com.example.lostfoundapp.ui.components.search.DateFilterRow
import com.example.lostfoundapp.ui.components.search.SearchFiltersSection
import com.example.lostfoundapp.ui.theme.HomeBodyBackground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.components.search.SearchInput

import com.example.lostfoundapp.ui.theme.FoundBadgeBackground
import com.example.lostfoundapp.ui.theme.FoundBadgeText
import com.example.lostfoundapp.ui.theme.LostBadgeBackground
import com.example.lostfoundapp.ui.theme.LostBadgeText

import androidx.compose.runtime.LaunchedEffect
import com.example.lostfoundapp.ui.viewmodel.PostsViewModel
import com.example.lostfoundapp.ui.viewmodel.SearchViewModel


@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    postsViewModel: PostsViewModel,

    currentRoute: String?,
    hasUnreadActivity: Boolean,
    onItemClick: (ItemPost) -> Unit,

    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onActivityClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    LaunchedEffect(Unit) {

        postsViewModel.loadCatalogs()
    }

    val posts =
        searchViewModel.searchResults

    LaunchedEffect(
        searchViewModel.selectedCategoryId,
        searchViewModel.selectedLocationId,
        searchViewModel.selectedDateFilter
    ) {

        searchViewModel.searchPosts(
            categoryId =
                searchViewModel.selectedCategoryId,

            locationId =
                searchViewModel.selectedLocationId,

            time =
                searchViewModel.selectedDateFilter.lowercase()
        )
    }

    val filteredPosts = posts.filter { post ->

        val matchesCategory =
            searchViewModel.selectedCategory.isEmpty() ||
            post.category == searchViewModel.selectedCategory

        val matchesLocation =
            searchViewModel.selectedLocation.isEmpty() ||
            post.location.contains(
                searchViewModel.selectedLocation,
                ignoreCase = true
            )

        val matchesSearch =
            searchViewModel.searchQuery.isEmpty() ||
            post.title.contains(
                searchViewModel.searchQuery,
                ignoreCase = true
            ) ||
            post.location.contains(
                searchViewModel.searchQuery,
                ignoreCase = true
            ) ||
            post.category.contains(
                searchViewModel.searchQuery,
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
                    value = searchViewModel.searchQuery,
                    onValueChange = {
                        searchViewModel.updateSearchQuery(it)
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
                        selectedCategory = searchViewModel.selectedCategory,
                        selectedLocation = searchViewModel.selectedLocation,

                        onCategoryClick = {
                            searchViewModel.showCategoryDialog()
                        },

                        onLocationClick = {
                            searchViewModel.showLocationDialog()
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DateFilterRow(
                        selectedFilter = searchViewModel.selectedDateFilter,
                        onFilterSelected = {
                            searchViewModel.updateDateFilter(it)
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

        if(searchViewModel.showCategoryDialog) {

            SelectionDialog(
                title = "Seleccionar categoría",
                options = postsViewModel.categories.map{it.name},
                selectedOption = searchViewModel.selectedCategory,

                onDismiss = {
                    searchViewModel.hideCategoryDialog()
                },

                onOptionSelected = { optionSelected ->

                    val selectedCategory = postsViewModel.categories.find {
                        it.name == optionSelected
                    }

                    searchViewModel.updateCategory(
                        id = selectedCategory?.id,
                        name = selectedCategory?.name ?: ""
                    )

                    searchViewModel.hideCategoryDialog()
                }
            )
        }

        if(searchViewModel.showLocationDialog) {

            SelectionDialog(
                title = "Seleccionar ubicación",
                options = postsViewModel.locations.map{it.name},
                selectedOption = searchViewModel.selectedLocation,

                onDismiss = {
                    searchViewModel.hideLocationDialog()
                },

                onOptionSelected = { optionSelected ->

                    val selectedLocation = postsViewModel.locations.find {
                        it.name == optionSelected
                    }

                    searchViewModel.updateLocation(
                        id = selectedLocation?.id,
                        name = selectedLocation?.name ?: ""
                    )

                    searchViewModel.hideLocationDialog()
                }
            )
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