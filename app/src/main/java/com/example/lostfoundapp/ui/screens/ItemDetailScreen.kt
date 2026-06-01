package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.lostfoundapp.data.mock.mockPosts
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.components.itemdetail.CreateRequestBottomSheet
import com.example.lostfoundapp.ui.components.ContactInfoBottomSheet
import com.example.lostfoundapp.ui.components.itemdetail.ItemDetailActionsRow
import com.example.lostfoundapp.ui.components.itemdetail.ItemHeroSection
import com.example.lostfoundapp.ui.components.itemdetail.ItemInfoSection
import com.example.lostfoundapp.ui.components.itemdetail.OwnershipNoticeSection
import com.example.lostfoundapp.ui.components.UserInfoSection
import com.example.lostfoundapp.ui.theme.BorderGray
import com.example.lostfoundapp.ui.theme.FoundActionCardForeground

@Composable
fun ItemDetailScreen(
    itemPost: ItemPost,
    onBackClick: () -> Unit
) {

    var showContactSheet by remember {
        mutableStateOf(false)
    }

    var showRequestSheet by remember {
        mutableStateOf(false)
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
                    reportType = itemPost.reportType,
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

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = BorderGray,
                        thickness = 1.dp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    UserInfoSection(
                        title = "Publicado por",
                        userName = itemPost.reporterName,
                        userImageRes = itemPost.reporterImageRes,
                        isAnonymous = itemPost.isAnonymous,
                        isContactVisible = itemPost.isContactVisible,
                        horizontalPadding = 20.dp,
                        showAsCard = false,

                        onClick = {
                            showContactSheet = true
                        }
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    OwnershipNoticeSection(
                        reportType = itemPost.reportType
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    ItemDetailActionsRow(
                        onShareClick = {

                        },

                        onSaveClick = {

                        },

                        onReportClick = {

                        }
                    )

                    Spacer(modifier = Modifier.height(140.dp))
                }
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
                    if(itemPost.reportType == ReportType.LOST)
                        "Tengo información"
                    else
                        "Solicitar reclamación",

                backgroundColor = FoundActionCardForeground,

                onClick = {
                    showRequestSheet = true
                }
            )
        }

        if (showContactSheet) {

            ContactInfoBottomSheet(
                userName = itemPost.reporterName,
                userImageRes = itemPost.reporterImageRes,
                email = "",
                phone = "",

                onDismiss = {
                    showContactSheet = false
                }
            )
        }

        if (showRequestSheet) {

            CreateRequestBottomSheet(
                reportType = itemPost.reportType,

                onDismiss = {
                    showRequestSheet = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemDetailScreenPreview() {

    ItemDetailScreen(
        itemPost = mockPosts[0],
        onBackClick = {}
    )
}