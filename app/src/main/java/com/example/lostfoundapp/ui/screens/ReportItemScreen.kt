package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity

import com.example.lostfoundapp.ui.components.DottedButton
import com.example.lostfoundapp.ui.components.CustomInput
import com.example.lostfoundapp.ui.components.UploadImageCard
import com.example.lostfoundapp.ui.components.DropdownInput
import com.example.lostfoundapp.ui.components.DescriptionInput
import com.example.lostfoundapp.ui.components.DateInput
import com.example.lostfoundapp.ui.components.VisibilitySwitch
import com.example.lostfoundapp.ui.components.BackButton
import com.example.lostfoundapp.data.model.ReportType

@Composable
fun ReportItemScreen(
    reportType: ReportType,
    onBackClick: () -> Unit
) {

    var hasImage by remember {
        mutableStateOf(false)
    }

    var objectName by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var location by remember {
        mutableStateOf("")
    }

    var category by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }

    var publicContact by remember {
        mutableStateOf(false)
    }


    // ERRORS

    var imageError by remember {
        mutableStateOf(false)
    }

    var objectNameError by remember {
        mutableStateOf(false)
    }

    var descriptionError by remember {
        mutableStateOf(false)
    }

    var locationError by remember {
        mutableStateOf(false)
    }

    var categoryError by remember {
        mutableStateOf(false)
    }

    var dateError by remember {
        mutableStateOf(false)
    }

    val isKeyboardVisible =
        WindowInsets.ime.getBottom(
            LocalDensity.current
        ) > 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(
                WindowInsets.safeDrawing
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // HEADER FIXED

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 16.dp
                    ),

                verticalAlignment = Alignment.CenterVertically
            ) {

                BackButton(
                    onClick = {
                        onBackClick()
                    }
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text =
                        if(reportType == ReportType.LOST)
                            "Objeto perdido"
                        else
                            "Objeto encontrado",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // SCROLLABLE CONTENT

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 24.dp,
                        bottom = 120.dp
                    )
            ) {

                UploadImageCard(
                    isError = imageError,
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Nombre del objeto",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                CustomInput(
                    value = objectName,
                    placeholder = "Ej. Mochila negra Jansport",
                    isError = objectNameError,
                    onValueChange = {
                        objectName = it
                        objectNameError = false
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Descripción",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                DescriptionInput(
                    value = description,
                    placeholder = "Características, color, marca, etc.",
                    isError = descriptionError,
                    onValueChange = {
                        description = it
                        descriptionError = false
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ubicación",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                DropdownInput(
                    text = location,
                    placeholder =
                        if(reportType == ReportType.LOST)
                            "Última ubicación conocida"
                        else
                            "Dónde lo encontraste",
                    isError = locationError,
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Categoría",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                DropdownInput(
                    text = category,
                    placeholder = "Seleccionar categoría",
                    isError = categoryError,
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Fecha",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                DateInput(
                    text = date,
                    placeholder = "Seleccionar fecha",
                    isError = dateError,
                    onClick = {

                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                VisibilitySwitch(
                    checked = publicContact,
                    onCheckedChange = {
                        publicContact = it
                    }
                )
            }
        }

        // FIXED BUTTON

        if (!isKeyboardVisible) {

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 16.dp
                    )
            ) {

                DottedButton(
                    bgColor = Color(0xFF03A2A6),
                    dotsColor = Color(0xFF0378A6),
                    text = "Publicar reporte",
                    onClick = {
                        imageError =
                            reportType == ReportType.FOUND
                                    && !hasImage

                        objectNameError =
                            objectName.isBlank()

                        descriptionError =
                            description.isBlank()

                        locationError =
                            reportType == ReportType.FOUND
                                    && location.isBlank()

                        categoryError =
                            category.isBlank()

                        dateError =
                            date.isBlank()

                        val hasErrors =
                            imageError ||
                                    objectNameError ||
                                    descriptionError ||
                                    locationError ||
                                    categoryError ||
                                    dateError

                        if (!hasErrors) {

                            println("FORMULARIO VÁLIDO")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportItemScreenPreview() {

    ReportItemScreen(
        reportType = ReportType.LOST,
        onBackClick = {}
    )
}