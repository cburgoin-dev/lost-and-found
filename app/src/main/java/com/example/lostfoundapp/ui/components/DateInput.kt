package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.ErrorRed
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun DateInput(
    text: String,
    placeholder: String,
    isError: Boolean = false,
    errorMessage: String = "Campo obligatorio",
    onClick: () -> Unit
) {

    val borderColor =
        if(isError)
            ErrorRed
        else
            BorderGray

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    1.dp,
                    borderColor,
                    RoundedCornerShape(12.dp)
                )
                .background(
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
                .clickable {
                    onClick()
                }
                .padding(horizontal = 16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = text.ifEmpty { placeholder },
                color = TextGray,
                fontSize = 16.sp,

                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = null,
                tint = TextGray
            )
        }

        if(isError) {

            Text(
                text = errorMessage,
                color = ErrorRed,
                fontSize = 13.sp,

                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                modifier = Modifier.padding(
                    start = 4.dp,
                    top = 4.dp
                )
            )
        }
    }
}