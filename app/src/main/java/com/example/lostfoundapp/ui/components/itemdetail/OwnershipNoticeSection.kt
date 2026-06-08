package com.example.lostfoundapp.ui.components.itemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostfoundapp.data.model.PostType

import com.example.lostfoundapp.ui.theme.HomeHeaderBlue

@Composable
fun OwnershipNoticeSection(
    postType: PostType,
    isOwner: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                color = HomeHeaderBlue.copy(alpha = 0.06f),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(18.dp),

        verticalAlignment = Alignment.Top
    ) {

        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            tint = HomeHeaderBlue,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {

            val title =

                if(isOwner) {
                    if(postType == PostType.FOUND)
                        "Estado de reclamación"
                    else
                        "Estado de búsqueda"

                } else {
                    if(postType == PostType.FOUND)
                        "Proceso de reclamación"
                    else
                        "Compartir información"
                }

            val description =

                if(isOwner) {
                    if(postType == PostType.FOUND)
                        "Los usuarios pueden enviar solicitudes para reclamar este objeto. Revisa las solicitudes recibidas desde Actividad."
                    else
                        "Tu publicación está visible para otros usuarios. Si alguien tiene información podrá enviarte una solicitud."
                } else {
                    if(postType == PostType.FOUND)
                        "Si este objeto es tuyo, puedes enviar una solicitud de reclamación. El usuario que realizó el reporte deberá confirmar la propiedad."
                    else
                        "Si tienes información sobre este objeto, puedes contactar al usuario o enviar detalles que ayuden a localizarlo."
                }

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                color = Color(0xFF5F6368)
            )
        }
    }
}