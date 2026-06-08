package com.example.lostfoundapp.ui.components.itemdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.components.DescriptionInput
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.LostActionCardForeground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportPostBottomSheet(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {

    var reason by remember {
        mutableStateOf("")
    }

    var reasonError by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val keyboardController =
        LocalSoftwareKeyboardController.current

    val focusManager =
        LocalFocusManager.current

    LaunchedEffect(sheetState.currentValue) {

        if(sheetState.currentValue == SheetValue.Hidden) {

            focusManager.clearFocus()

            keyboardController?.hide()
        }
    }

    ModalBottomSheet(
        onDismissRequest = {

            focusManager.clearFocus()

            keyboardController?.hide()

            onDismiss()
        },
        containerColor = Color.White,
        sheetState = sheetState
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 8.dp,
                )
        ) {

            Text(
                text = "Reportar publicación",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Indica el motivo del reporte.",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = DetailSecondaryText
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            DescriptionInput(
                value = reason,
                placeholder = "Describe el motivo",
                isError = reasonError,
                onValueChange = {

                    reason = it
                    reasonError = false
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "El reporte será revisado por moderación.",
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = DetailSecondaryText
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            PrimaryButton(
                text = "Enviar reporte",
                backgroundColor = LostActionCardForeground,
                height = 52.dp,
                onClick = {

                    reasonError = reason.isBlank()

                    if(reason.isBlank()) {
                        return@PrimaryButton
                    }

                    focusManager.clearFocus()

                    keyboardController?.hide()

                    onConfirm(reason)
                }
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}