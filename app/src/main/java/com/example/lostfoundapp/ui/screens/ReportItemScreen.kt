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
import androidx.compose.ui.platform.LocalContext
import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import java.util.Calendar

import com.example.lostfoundapp.ui.components.CustomInput
import com.example.lostfoundapp.ui.components.UploadImageCard
import com.example.lostfoundapp.ui.components.DropdownInput
import com.example.lostfoundapp.ui.components.DescriptionInput
import com.example.lostfoundapp.ui.components.DateInput
import com.example.lostfoundapp.ui.components.VisibilitySwitch
import com.example.lostfoundapp.ui.components.BackButton
import com.example.lostfoundapp.ui.components.SelectionDialog
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.mock.categories
import com.example.lostfoundapp.data.mock.locations
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.theme.FoundActionCardForeground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.LostActionCardForeground

@Composable
fun ReportItemScreen(
    reportType: ReportType,
    onBackClick: () -> Unit
) {

    var hasImage by remember {
        mutableStateOf(false)
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
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

    var showCategoryDialog by remember {
        mutableStateOf(false)
    }

    var showLocationDialog by remember {
        mutableStateOf(false)
    }

    val isKeyboardVisible =
        WindowInsets.ime.getBottom(
            LocalDensity.current
        ) > 0

    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.GetContent()
        ) { uri ->

            if(uri != null) {

                selectedImageUri = uri
                hasImage = true
                imageError = false
            }
        }

    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,

        { _, year, month, dayOfMonth ->

            date =
                "$dayOfMonth/${month  + 1}/$year"

            dateError = false
        },

        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

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
                        onClick = {
                            onBackClick()
                        },

                        hasBackground = false,
                        iconColor = Color.White
                    )

                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Nuevo reporte",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(42.dp))
                }
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
                        top = 28.dp,
                        bottom = 98.dp
                    )
            ) {

                UploadImageCard(
                    imageUri = selectedImageUri,
                    isError = imageError,
                    onClick = {

                        imagePickerLauncher.launch(
                            "image/*"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Nombre del objeto",
                    fontWeight = FontWeight.Medium,
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
                    fontWeight = FontWeight.Medium,
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
                    fontWeight = FontWeight.Medium,
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
                        showLocationDialog = true
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Categoría",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                DropdownInput(
                    text = category,
                    placeholder = "Seleccionar categoría",
                    isError = categoryError,
                    onClick = {
                        showCategoryDialog = true
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Fecha",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                DateInput(
                    text = date,
                    placeholder = "Seleccionar fecha",
                    isError = dateError,
                    onClick = {
                        datePickerDialog.show()
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

                PrimaryButton(
                    text = "Publicar reporte",
                    backgroundColor =
                        if(reportType == ReportType.LOST)
                            LostActionCardForeground
                        else
                            FoundActionCardForeground,
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

        if (showCategoryDialog) {

            SelectionDialog(
                title = "Seleccionar categoría",
                options = categories,

                onDismiss = {
                    showCategoryDialog = false
                },

                onOptionSelected = {

                    category = it
                    categoryError = false
                    showCategoryDialog = false
                }
            )
        }

        if(showLocationDialog) {

            SelectionDialog(
                title = "Seleccionar ubicación",
                options = locations,

                onDismiss = {
                    showLocationDialog = false
                },

                onOptionSelected = {

                    location = it
                    locationError = false
                    showLocationDialog = false
                }
            )
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