package com.example.lostfoundapp.ui.components.itemdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.ui.components.DescriptionInput
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.viewmodel.RequestsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRequestBottomSheet(
    postId: Int,
    postType: PostType,
    requestsViewModel: RequestsViewModel,
    onDismiss: () -> Unit
) {

    val title =
        if(postType == PostType.LOST)
            "Compartir información"
        else
            "Solicitar reclamación"

    val description =
        if(postType == PostType.LOST)
            "Comparte detalles útiles que puedan ayudar a localizar el objeto."
        else
            "Describe características del objeto para ayudar al usuario a verificar la propiedad."

    var primaryInput by remember {
        mutableStateOf("")
    }

    var primaryInputError by remember {
        mutableStateOf(false)
    }

    var secondaryInput by remember {
        mutableStateOf("")
    }

    val primaryLabel =
        if(postType == PostType.LOST)
            "¿Dónde lo viste?"
        else
            "Describe características del objeto"

    val secondaryLabel =
        if(postType == PostType.LOST)
            "Detalles adicionales (opcional)"
        else
            "Mensaje adicional (opcional)"

    val contactInfoText =
        if (postType == PostType.LOST)
            "Tu información de contacto será compartida únicamente si el propietario aprueba esta información."
        else
            "Tu información de contacto será compartida únicamente si la solicitud es aprobada."

    val buttonText =
        if(postType == PostType.LOST)
            "Enviar información"
        else
            "Enviar solicitud"

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val keyboardController =
        LocalSoftwareKeyboardController.current

    val focusManager =
        LocalFocusManager.current

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
                    vertical = 8.dp
                )
        ) {

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = description,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = DetailSecondaryText
            )

            Spacer(modifier = Modifier.height(28.dp))

            DescriptionInput(
                value = primaryInput,
                placeholder = primaryLabel,
                isError = primaryInputError,
                onValueChange = {
                    primaryInput = it
                    primaryInputError = false
                },
            )

            Spacer(modifier = Modifier.height(16.dp))

            DescriptionInput(
                value = secondaryInput,
                placeholder = secondaryLabel,
                onValueChange = {
                    secondaryInput = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = contactInfoText,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = DetailSecondaryText
            )

            Spacer(modifier = Modifier.height(20.dp))

            PrimaryButton(
                text = buttonText,
                height = 52.dp,
                onClick = {
                    primaryInputError = primaryInput.isBlank()

                    if(primaryInput.isBlank()){
                        return@PrimaryButton
                    }

                    focusManager.clearFocus()

                    keyboardController?.hide()

                    requestsViewModel.createRequest(

                        postId = postId,

                        content = primaryInput,

                        message = secondaryInput,

                        onSuccess = {

                            onDismiss()
                        }
                    )
                }
            )
        }
    }
}