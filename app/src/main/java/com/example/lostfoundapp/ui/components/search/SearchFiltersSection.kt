package com.example.lostfoundapp.ui.components.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lostfoundapp.data.mock.categories
import com.example.lostfoundapp.data.mock.locations
import com.example.lostfoundapp.ui.components.DropdownInput
import com.example.lostfoundapp.ui.components.SelectionDialog

@Composable
fun SearchFiltersSection(
    selectedCategory: String,
    selectedLocation: String,
    onCategoryClick: () -> Unit,
    onLocationClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Box(
            modifier = Modifier.weight(1f)
        ) {

            DropdownInput(
                text = selectedCategory,
                placeholder = "Categoría",
                onClick = {
                    onCategoryClick()
                }
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {

            DropdownInput(
                text = selectedLocation,
                placeholder = "Ubicación",
                onClick = {
                    onLocationClick()
                }
            )
        }
    }
}