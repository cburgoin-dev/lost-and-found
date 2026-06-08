package com.example.lostfoundapp.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.ui.components.ConfirmationBottomSheet
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.components.itemdetail.CreateRequestBottomSheet
import com.example.lostfoundapp.ui.components.ContactInfoBottomSheet
import com.example.lostfoundapp.ui.components.itemdetail.ItemDetailActionsRow
import com.example.lostfoundapp.ui.components.itemdetail.ItemHeroSection
import com.example.lostfoundapp.ui.components.itemdetail.ItemInfoSection
import com.example.lostfoundapp.ui.components.itemdetail.OwnershipNoticeSection
import com.example.lostfoundapp.ui.components.UserInfoSection
import com.example.lostfoundapp.ui.components.itemdetail.ReportPostBottomSheet
import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.FoundActionCardForeground
import com.example.lostfoundapp.ui.viewmodel.PostsViewModel
import com.example.lostfoundapp.ui.viewmodel.RequestsViewModel

@Composable
fun ItemDetailScreen(
    itemPost: ItemPost,
    postsViewModel: PostsViewModel,
    requestsViewModel: RequestsViewModel,
    onBackClick: () -> Unit
) {

    val context = LocalContext.current

    var showContactSheet by remember {
        mutableStateOf(false)
    }

    var showRequestSheet by remember {
        mutableStateOf(false)
    }

    var showReportSheet by remember {
        mutableStateOf(false)
    }

    var showClosePostSheet by remember {
        mutableStateOf(false)
    }

    val currentPost =
        postsViewModel.findPostById(itemPost.id)
            ?: itemPost

    val isOwner = currentPost.isMine

    var showSuccessMessage by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(showSuccessMessage) {

        if(showSuccessMessage) {

            kotlinx.coroutines.delay(3000)

            showSuccessMessage = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {

                ItemHeroSection(
                    imageUrl = itemPost.imageUrl,
                    postType = itemPost.postType,
                    onBackClick = onBackClick
                )
            }

            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {

                    Spacer(modifier = Modifier.height(24.dp))

                    ItemInfoSection(
                        itemPost = itemPost
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = BorderGray,
                        thickness = 1.dp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    UserInfoSection(
                        title =
                            if(isOwner)
                                "Tu publicación"
                            else
                                "Publicado por",
                        userName =
                            if(isOwner)
                                "Tú"
                            else
                                itemPost.publisherName,
                        userImageRes = itemPost.publisherImageRes,
                        userImageUrl = itemPost.publisherImageUrl,
                        isAnonymous = itemPost.isAnonymous,
                        isContactVisible =
                            if(isOwner)
                                false
                            else
                                itemPost.isContactVisible,
                        horizontalPadding = 20.dp,
                        secondaryText =
                            if(isOwner)
                                "Eres el propietario de esta publicación."
                            else
                                null,
                        showAsCard = false,

                        onClick =
                            if(isOwner)
                                null
                            else {
                                { showContactSheet = true }
                            }
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    OwnershipNoticeSection(
                        postType = itemPost.postType,
                        isOwner = isOwner
                    )

                    if(!isOwner) {

                        Spacer(modifier = Modifier.height(28.dp))

                        ItemDetailActionsRow(
                            isSaved = currentPost.isBookmarked,
                            isReported = postsViewModel.isReported,

                            onShareClick = {

                            },

                            onSaveClick = {

                                val message =
                                    if(currentPost.isBookmarked)
                                        "Publicación eliminada de guardados"
                                    else
                                        "Publicación guardada"

                                postsViewModel.toggleBookmark(itemPost.id)

                                Toast.makeText(
                                    context,
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            },

                            onReportClick = {
                                showReportSheet = true
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(140.dp))
                }
            }
        }
        AnimatedVisibility(
            visible = showSuccessMessage,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start =6.dp,
                    end = 6.dp,
                    bottom = 120.dp


                )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF4CAF50),
                        RoundedCornerShape(12.dp)
                    )
            ) {

                BasicText(
                    text = "Solicitud de contacto enviada con exito",
                    style = TextStyle(color = Color.White),
                    modifier = Modifier.padding(
                        horizontal = 26.dp,
                        vertical = 8.dp
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .navigationBarsPadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {

            PrimaryButton(
                text =
                    when {

                        isOwner && itemPost.postType == PostType.LOST ->
                            "Ya recuperé el objeto"

                        isOwner && itemPost.postType == PostType.FOUND ->
                            "Ya entregué el objeto"

                        itemPost.postType == PostType.LOST ->
                            "Tengo información"

                        else ->
                            "Solicitar reclamación"
                    },

                backgroundColor = FoundActionCardForeground,

                onClick = {

                    if(isOwner) {

                        showClosePostSheet = true

                    } else {

                        showRequestSheet = true
                    }
                }
            )
        }

        if (showContactSheet) {

            ContactInfoBottomSheet(
                userName = itemPost.publisherName,
                userImageRes = itemPost.publisherImageRes,
                userImageUrl = itemPost.publisherImageUrl,
                email = itemPost.publisherEmail,
                phone = itemPost.publisherPhone,

                onDismiss = {
                    showContactSheet = false
                }
            )
        }

        if (showRequestSheet) {

            CreateRequestBottomSheet(
                postId = itemPost.id,

                postType = itemPost.postType,

                requestsViewModel = requestsViewModel,
                onSuccess = {

                    showRequestSheet = false
                    showSuccessMessage = true
                },
                onDismiss = {
                    showRequestSheet = false
                }
            )
        }

        if (showReportSheet) {

            ReportPostBottomSheet(

                onConfirm = { reason ->

                    postsViewModel.reportPost(
                        postId = itemPost.id,
                        reason = reason
                    )

                    Toast.makeText(
                        context,
                        "Reporte enviado correctamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    showReportSheet = false
                },

                onDismiss = {
                    showReportSheet = false
                }
            )
        }

        if (showClosePostSheet) {

            ConfirmationBottomSheet(

                title =
                    if(itemPost.postType == PostType.LOST)
                        "¿Ya recuperaste el objeto?"
                    else
                        "¿Ya entregaste el objeto?",

                description =
                    if(itemPost.postType == PostType.LOST)
                        "La publicación dejará de aparecer en las búsquedas y se marcará como resuelta."
                    else
                        "La publicación dejará de aparecer en las búsquedas y se marcará como resuelta.",

                buttonText =
                    if(itemPost.postType == PostType.LOST)
                        "Sí, ya lo recuperé"
                    else
                        "Sí, ya lo entregué",

                buttonColor = FoundActionCardForeground,

                onConfirm = {

                    postsViewModel.completePost(
                        itemPost.id
                    ) {

                        showClosePostSheet = false
                        postsViewModel.notifyPostCompleted()
                        onBackClick()
                    }
                },

                onDismiss = {

                    showClosePostSheet = false
                }

            )
        }
    }
}