package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import coil.compose.SubcomposeAsyncImage

import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInfoBottomSheet(
    userName: String,
    userImageRes: Int?,
    userImageUrl: String?,
    email: String?,
    phone: String?,
    onDismiss: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val clipboardManager = LocalClipboardManager.current

    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        sheetState = sheetState
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp
                ),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!userImageUrl.isNullOrBlank()) {

                SubcomposeAsyncImage(

                    model = userImageUrl,

                    contentDescription = null,

                    modifier = Modifier
                        .size(112.dp)
                        .clip(CircleShape),

                    contentScale = ContentScale.Crop,

                    loading = {
                        ContactPlaceholder()
                    },

                    error = {
                        ContactPlaceholder()
                    }
                )

            } else {

                ContactPlaceholder()
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = userName,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (!email.isNullOrBlank()) {

                ContactInfoItem(
                    icon = Icons.Outlined.Email,
                    text = email,
                    onClick = {
                        clipboardManager.setText(
                            AnnotatedString(email)
                        )

                        Toast.makeText(
                            context,
                            "Correo copiado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            if (!phone.isNullOrBlank()) {

                ContactInfoItem(
                    icon = Icons.Outlined.Phone,
                    text = phone,
                    onClick = {
                        clipboardManager.setText(
                            AnnotatedString(phone)
                        )

                        Toast.makeText(
                            context,
                            "Número copiado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Puedes contactar directamente a esta persona para coordinar la entrega o recuperación del objeto.",
                fontSize = 14.sp,
                lineHeight = 22.sp,
                color = DetailSecondaryText
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun ContactInfoItem(
    icon: ImageVector,
    text: String?,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF7F8FA))
            .clickable {
                onClick()
            }
            .padding(16.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = HomeHeaderBlue
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = text ?: "No disponible",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Outlined.ContentCopy,
            contentDescription = null,
            tint = HomeHeaderBlue,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun ContactPlaceholder() {

    Box(
        modifier = Modifier
            .size(112.dp)
            .clip(CircleShape)
            .background(Color(0xFFF1F1F1)),

        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = null,
            tint = TextGray,
            modifier = Modifier.size(52.dp)
        )
    }
}