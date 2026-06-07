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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalContext
import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.theme.FoundActionCardForeground
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue
import com.example.lostfoundapp.ui.theme.LostActionCardForeground
import com.example.lostfoundapp.ui.viewmodel.PostsViewModel

@Composable
fun ReportItemScreen(
    reportType: ReportType,
    postsViewModel: PostsViewModel,
    onBackClick: () -> Unit,
    onPostCreated: () -> Unit
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

    var categoryId by remember {
        mutableStateOf<Int?>(null)
    }

    var locationId by remember {
        mutableStateOf<Int?>(null)
    }
    var date by remember {
        mutableStateOf("")
    }

    var publicContact by remember {
        mutableStateOf(false)
    }

    // VALIDATIONS

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
                "$year-${month+1}-$dayOfMonth"

            dateError = false
        },

        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val focusManager = LocalFocusManager.current

    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current

    LaunchedEffect(Unit) {

        postsViewModel.loadCatalogs()
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
                            focusManager.clearFocus()
                            keyboardController?.hide()
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
                            text = "Nueva publicación",
                            color = Color.White,
                            fontSize = 24.sp,
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Mostrar mi contacto públicamente",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )

                    VisibilitySwitch(
                        checked = publicContact,
                        onCheckedChange = {
                            publicContact = it
                        }
                    )
                }
            }
        }

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
                    text =
                        if(postsViewModel.isLoading)
                            "Creando publicación..."
                        else
                            "Crear publicación",
                    backgroundColor =
                        if(reportType == ReportType.LOST)
                            LostActionCardForeground
                        else
                            FoundActionCardForeground,
                    enabled = !postsViewModel.isLoading,
                    onClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()

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

                            postsViewModel.createPost(
                                context = context,
                                reportType = reportType,
                                objectName = objectName,
                                description = description,
                                locationId = locationId!!,
                                categoryId = categoryId!!,
                                date = date,
                                publicContact = publicContact,
                                selectedImageUri = selectedImageUri,
                                onSuccess = {
                                    onPostCreated()
                                }
                            )
                        }
                    }
                )
            }
        }

        if (showCategoryDialog) {

            SelectionDialog(
                title = "Seleccionar categoría",
                options = postsViewModel.categories.map{it.name},
                selectedOption = category,

                onDismiss = {
                    showCategoryDialog = false
                },

                onOptionSelected = { selectedName ->

                    val selectedCategory =
                        postsViewModel.categories.find { it.name == selectedName }

                    category =
                        selectedCategory?.name ?: ""

                    categoryId =
                        selectedCategory?.id

                    categoryError = false
                    showCategoryDialog = false
                }
            )
        }

        if(showLocationDialog) {

            SelectionDialog(
                title = "Seleccionar ubicación",
                options = postsViewModel.locations.map{it.name},
                selectedOption = location,

                onDismiss = {
                    showLocationDialog = false
                },

                onOptionSelected = { selectedName ->

                    val selectedLocation =
                        postsViewModel.locations.find { it.name == selectedName }

                    location =
                        selectedLocation?.name ?: ""

                    locationId =
                        selectedLocation?.id

                    locationError = false
                    showLocationDialog = false
                }
            )
        }
    }
}