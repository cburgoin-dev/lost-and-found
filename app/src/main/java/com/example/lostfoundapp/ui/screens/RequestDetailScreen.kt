package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.data.model.RequestType
import com.example.lostfoundapp.ui.components.BackButton
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.components.SecondaryButton
import com.example.lostfoundapp.ui.components.activity.ConfirmationBottomSheet
import com.example.lostfoundapp.ui.components.activity.RequestContextSection
import com.example.lostfoundapp.ui.components.activity.RequestMessageSection
import com.example.lostfoundapp.ui.components.activity.RequestStatusSection
import com.example.lostfoundapp.ui.components.ContactInfoBottomSheet
import com.example.lostfoundapp.ui.components.UserInfoSection
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.LostActionCardForeground

@Composable
fun RequestDetailScreen(
    request: Request,
    onBackClick: () -> Unit,
    onApproveClick: () -> Unit,
    onRejectClick: () -> Unit
) {

    var showContactSheet by remember {
        mutableStateOf(false)
    }

    var showApproveSheet by remember {
        mutableStateOf(false)
    }

    var showRejectSheet by remember {
        mutableStateOf(false)
    }

    val secondaryText =
        when (request.status) {
            RequestStatus.APPROVED ->
                "Toca para ver la información de contacto."

            RequestStatus.REJECTED ->
                "La información de contacto no está disponible."

            else -> "La información de contacto estará disponible después de aprobar la solicitud."
        }

    val userName =
        when (request.status) {
            RequestStatus.APPROVED ->
                request.sender.fullName

            RequestStatus.REJECTED ->
                "Usuario anónimo"

            else -> "Usuario pendiente"
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(
                WindowInsets.safeDrawing
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .background(HomeHeaderBlue)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    BackButton(
                        onClick = onBackClick,
                        hasBackground = false,
                        iconColor = Color.White
                    )

                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text =
                                if (request.requestType == RequestType.CLAIM)
                                    "Solicitud"
                                else
                                    "Información",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(42.dp)
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    horizontal = 24.dp,
                    vertical = 24.dp
                )
            ) {

                item {

                    AsyncImage(
                        model = request.itemImageRes,
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(20.dp)),

                        contentScale = ContentScale.Fit
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    Text(
                        text = request.itemName,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 34.sp
                    )
                }

                item {

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    RequestContextSection(request)
                }

                item {

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    RequestMessageSection(
                        title =
                            if (request.requestType == RequestType.CLAIM)
                                "Mensaje del solicitante"
                            else
                                "Información compartida",

                        message = request.description
                    )
                }

                if (request.additionalMessage.isNotBlank()) {

                    item {

                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )

                        RequestMessageSection(
                            title =
                                if (request.requestType == RequestType.CLAIM)
                                    "Información adicional"
                                else
                                    "Detalles adicionales",

                            message = request.additionalMessage
                        )
                    }
                }

                item {

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    UserInfoSection(
                        title =
                            if (request.requestType == RequestType.CLAIM)
                                "Solicitante"
                            else
                                "Usuario",
                        userName = userName,
                        userImageRes =
                            if (request.status == RequestStatus.APPROVED)
                                request.sender.profileImageRes
                            else
                                null,
                        isAnonymous = false,
                        isContactVisible =
                            request.status == RequestStatus.APPROVED,
                        horizontalPadding = 0.dp,
                        secondaryText = secondaryText,
                        showAsCard = true,
                        onClick = {
                            showContactSheet = true
                        }
                    )

                    if (
                        request.status != RequestStatus.PENDING
                    ) {

                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )

                        RequestStatusSection(
                            status = request.status
                        )
                    }
                }

                item {

                    Spacer(
                        modifier = Modifier.height(
                            if (
                                request.status == RequestStatus.PENDING
                            )
                                72.dp
                            else
                                0.dp
                        )
                    )
                }
            }
        }

        if (
            request.status == RequestStatus.PENDING
        ) {

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White)
                    .navigationBarsPadding()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 16.dp
                    ),

                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                SecondaryButton(
                    text = "Rechazar",
                    backgroundColor = LostActionCardForeground,
                    textColor = Color.White,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        showRejectSheet = true
                    }
                )

                PrimaryButton(
                    text = "Aprobar",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        showApproveSheet = true
                    }
                )
            }
        }

        if (showContactSheet) {

            ContactInfoBottomSheet(
                userName = request.sender.fullName,
                userImageRes = request.sender.profileImageRes,
                email = request.sender.email,
                phone = request.sender.phone,

                onDismiss = {
                    showContactSheet = false
                }
            )
        }

        if (showApproveSheet) {

            ConfirmationBottomSheet(
                title = "Aprobar solicitud",
                description = "Al aprobar esta solicitud, ambos usuarios podrán acceder a la información de contacto para continuar la comunicación.",
                buttonText = "Aprobar solicitud",
                buttonColor = HomeHeaderBlue,

                onConfirm = {
                    showApproveSheet = false
                    onApproveClick()
                },

                onDismiss = {
                    showApproveSheet = false
                }
            )
        }

        if (showRejectSheet) {

            ConfirmationBottomSheet(
                title = "Rechazar solicitud",
                description = "La solicitud será rechazada y la información de contacto no será compartida.",
                buttonText = "Rechazar solicitud",
                buttonColor = LostActionCardForeground,

                onConfirm = {
                    showRejectSheet = false
                    onRejectClick()
                },

                onDismiss = {
                    showRejectSheet = false
                }
            )
        }
    }
}