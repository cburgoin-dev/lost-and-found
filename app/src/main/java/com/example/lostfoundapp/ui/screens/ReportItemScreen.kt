package com.example.lostfoundapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.components.DottedButton
import com.example.lostfoundapp.ui.components.CustomInput
import com.example.lostfoundapp.ui.components.UploadImageCard

@Composable
fun ReportItemScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
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

        Spacer(modifier = Modifier.height(6.dp))

        CustomInput(
            value = "",
            placeholder = "Ej. Mochila negra Jansport",
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomInput(
            value = "",
            placeholder = "¿Dónde lo viste?",
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomInput(
            value = "",
            placeholder = "Electrónicos, accesorios...",
            onValueChange = {}
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