package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

import com.example.lostfoundapp.ui.theme.TextGray

@Composable
fun UserInfoSection(
    title: String,
    userName: String,
    userImageRes: Int?,
    userImageUrl: String?,
    isAnonymous: Boolean,
    isContactVisible: Boolean,
    horizontalPadding: Dp = 20.dp,
    secondaryText: String? = null,
    showAsCard: Boolean = false,
    onClick: (() -> Unit)? = null
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
    ) {

        Text(
            text = title,
            color = HomeHeaderBlue,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(
                    when {
                        showAsCard ->
                            HomeHeaderBlue.copy(alpha = 0.06f)

                        isContactVisible ->
                            HomeHeaderBlue.copy(alpha = 0.08f)

                        else ->
                            Color.Transparent
                    }
                )
                .clickable(
                    enabled = isContactVisible && onClick != null
                ) {
                    onClick?.invoke()
                }
                .padding(14.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            if(isAnonymous) {

                UserPlaceholder()

            } else if (!userImageUrl.isNullOrBlank()) {

                SubcomposeAsyncImage(

                    model = userImageUrl,

                    contentDescription = null,

                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape),

                    contentScale = ContentScale.Crop,

                    loading = {
                        UserPlaceholder()
                    },

                    error = {
                        UserPlaceholder()
                    }
                )

            } else if (userImageRes != null) {

                Image(
                    painter = painterResource(userImageRes),
                    contentDescription = null,

                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape),

                    contentScale = ContentScale.Crop
                )

            } else {

                UserPlaceholder()
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text =
                        if(isAnonymous)
                            "Usuario anónimo"
                        else
                            userName,

                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,

                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = secondaryText ?: run {

                        if (isAnonymous || !isContactVisible)
                            "La información será compartida durante el proceso de contacto."
                        else
                            "Toca para ver la información de contacto."
                    },

                    color = DetailSecondaryText,
                    fontSize = 14.sp
                )
            }

            if (isContactVisible) {

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                    contentDescription = null,
                    tint = DetailSecondaryText,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun UserPlaceholder() {

    Box(
        modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(Color(0xFFF1F1F1)),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = null,
            tint = TextGray,
            modifier = Modifier.size(28.dp)
        )
    }
}