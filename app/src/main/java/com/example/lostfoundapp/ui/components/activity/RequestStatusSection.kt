package com.example.lostfoundapp.ui.components.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.model.RequestStatus
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun RequestStatusSection(
    status: RequestStatus
) {

    val isApproved =
        status == RequestStatus.APPROVED

    val backgroundColor =
        if (isApproved)
            Color(0xFFE8F5E9)
        else
            Color(0xFFFFEBEE)

    val titleColor =
        if (isApproved)
            Color(0xFF2E7D32)
        else
            Color(0xFFC62828)

    val title =
        if(status == RequestStatus.APPROVED)
            "Solicitud aprobada"
        else
            "Solicitud rechazada"

    val description =
        if(status == RequestStatus.APPROVED)
            "Ahora ambos usuarios pueden acceder a la información de contacto para continuar la comunicación."
        else
            "La información de contacto no será compartida para esta solicitud."

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                backgroundColor,
                RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {

        Text(
            text = title,
            color = titleColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = description,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
    }
}