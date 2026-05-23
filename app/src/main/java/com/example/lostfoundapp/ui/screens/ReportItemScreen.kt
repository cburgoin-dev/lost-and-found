package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.components.DottedButton
import com.example.lostfoundapp.ui.components.CustomInput
import com.example.lostfoundapp.ui.components.UploadImageCard
import com.example.lostfoundapp.ui.components.DropdownInput
import com.example.lostfoundapp.ui.components.DescriptionInput
import com.example.lostfoundapp.ui.components.DateInput
import com.example.lostfoundapp.ui.components.VisibilitySwitch

@Composable
fun ReportItemScreen() {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 16.dp,
                bottom = 90.dp
            )
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Nuevo reporte",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        UploadImageCard(
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
            onValueChange = {
                objectName = it
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
            onValueChange = {
                description = it
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
            placeholder = "Seleccionar ubicación",
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

        Spacer(modifier = Modifier.height(24.dp))

        DottedButton(
            bgColor = Color(0xFF03A2A6),
            dotsColor = Color(0xFF0378A6),
            text = "Publicar reporte",
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReportItemScreenPreview() {

    ReportItemScreen()
}