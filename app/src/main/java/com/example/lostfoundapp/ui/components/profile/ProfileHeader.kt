package com.example.lostfoundapp.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lostfoundapp.ui.components.PrimaryButton
import com.example.lostfoundapp.ui.components.VisibilitySwitch

@Composable
fun ProfileHeader(
    userName: String,
    email: String,
    phone: String,
    isContactVisible: Boolean,
    onEditProfileClick: () -> Unit,
    onVisibilityChange: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = userName
        )

        Text(
            text = email
        )

        Text(
            text = phone
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Editar perfil",
            onClick = onEditProfileClick,
            modifier = Modifier.width(180.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        VisibilitySwitch(
            checked = isContactVisible,
            onCheckedChange = onVisibilityChange
        )
    }
}