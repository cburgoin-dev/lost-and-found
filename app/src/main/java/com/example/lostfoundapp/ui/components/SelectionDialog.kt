package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectionDialog(
    title: String,
    options: List<String>,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },

        title = {
            Text(text = title)
        },

        text = {

            LazyColumn {

                items(options) { option ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                onOptionSelected(option)
                            }
                            .padding(vertical = 12.dp)
                    ) {

                        Text(text = option)
                    }
                }
            }
        },

        confirmButton = {

            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {

                Text("Cerrar")
            }
        }
    )
}