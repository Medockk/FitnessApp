package com.example.common

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._C6C4D4

@Composable
fun CustomSwitch(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedTrackColor = _228F7D,
            checkedThumbColor = Color.White,
            checkedBorderColor = Color.Transparent,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = _C6C4D4,
            uncheckedBorderColor = Color.Transparent
        ),
        modifier = modifier
    )
}