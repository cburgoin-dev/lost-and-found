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
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.model.RequestType
import com.example.lostfoundapp.ui.theme.DetailSecondaryText
import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun RequestContextSection(
    request: Request
) {

    val description =
        if (request.requestType == RequestType.CLAIM)
            "${request.sender.fullName} envió una solicitud para reclamar este objeto."
        else
            "${request.sender.fullName} compartió información que podría ayudar a localizar este objeto."

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = HomeHeaderBlue.copy(alpha = 0.05f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(18.dp)
    ) {

        Text(
            text = description,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Recibida el ${request.createdAt}",
            fontSize = 13.sp,
            color = DetailSecondaryText
        )
    }
}