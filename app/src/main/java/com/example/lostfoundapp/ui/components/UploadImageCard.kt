package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import coil.compose.AsyncImage

import com.example.lostfoundapp.ui.theme.BackgroundGray
import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.ErrorRed
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun UploadImageCard(
    imageUri: Uri? = null,
    imageUrl: String? = null,
    isError: Boolean = false,
    errorMessage: String = "Foto obligatoria",
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(165.dp)
                .border(
                    border = BorderStroke(
                        1.dp,
                        borderColor
                    ),
                    shape = RoundedCornerShape(14.dp)
                )
                .background(
                    BackgroundGray,
                    RoundedCornerShape(14.dp)
                )
                .clickable {
                    onClick()
                },

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if(imageUri != null || imageUrl != null) {

                Box {

                    AsyncImage(
                        model = imageUri ?: imageUrl,
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(165.dp),

                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                            .background(
                                Color.Black.copy(alpha = 0.45f),
                                RoundedCornerShape(100.dp)
                            )
                            .clickable {
                                onClick()
                            }
                            .padding(
                                horizontal = 12.dp,
                                vertical = 6.dp
                            )
                    ) {

                        Text(
                            text = "Cambiar",
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                }
            } else {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Outlined.CameraAlt,
                        contentDescription = null,
                        tint = TextGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Toca para subir foto",
                        color = TextGray,
                        fontSize = 16.sp
                    )
                }
            }
        }

        if(isError) {

            Text(
                text = errorMessage,
                color = ErrorRed,
                fontSize = 13.sp,
                modifier = Modifier.padding(
                    start = 4.dp,
                    top = 4.dp
                )
            )
        }
    }
}