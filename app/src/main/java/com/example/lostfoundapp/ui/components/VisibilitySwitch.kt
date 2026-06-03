package com.example.lostfoundapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import com.example.lostfoundapp.ui.theme.SwitchCheckedThumb
import com.example.lostfoundapp.ui.theme.SwitchCheckedTrack
import com.example.lostfoundapp.ui.theme.SwitchUncheckedThumb
import com.example.lostfoundapp.ui.theme.SwitchUncheckedTrack

@Composable
fun VisibilitySwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,

        colors = SwitchDefaults.colors(
            checkedThumbColor = SwitchCheckedThumb,
            checkedTrackColor = SwitchCheckedTrack,

            uncheckedThumbColor = SwitchUncheckedThumb,
            uncheckedTrackColor = SwitchUncheckedTrack,

            uncheckedBorderColor= Color.Transparent,
            checkedBorderColor = Color.Transparent
        )
    )
}