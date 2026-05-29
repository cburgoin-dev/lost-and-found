package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun SelectionDialog(
    title: String,
    options: List<String>,
    selectedOption: String,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit
) {

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },

        containerColor = Color.White,

        shape = RoundedCornerShape(24.dp),

        title = {

            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },

        text = {

            LazyColumn(
                modifier = Modifier.heightIn(max = 350.dp)
            ) {

                items(options) { option ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onOptionSelected(option)
                            }
                            .padding(vertical = 14.dp),

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = option,

                            fontSize = 16.sp,

                            color =
                                if(option == selectedOption)
                                    HomeHeaderBlue
                                else
                                    Color.Black,

                            fontWeight =
                                if(option == selectedOption)
                                    FontWeight.SemiBold
                                else
                                    FontWeight.Normal
                        )

                        if(option == selectedOption) {

                            Icon(
                                imageVector = Icons.Outlined.Check,
                                contentDescription = null,
                                tint = HomeHeaderBlue
                            )
                        }
                    }
                }
            }
        },

        confirmButton = {

            Row {

                TextButton(
                    onClick = {
                        onOptionSelected("")
                        onDismiss()
                    }
                ) {

                    Text(
                        text = "Limpiar",
                        color = HomeHeaderBlue
                    )
                }

                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {

                    Text(
                        text = "Cerrar",
                        color = HomeHeaderBlue
                    )
                }
            }
        }
    )
}