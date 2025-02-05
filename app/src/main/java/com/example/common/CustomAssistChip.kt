package com.example.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme._DDDADA

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    CustomAssistChip(ImageVector.vectorResource(R.drawable.google_icon)) { }
}

@Composable
fun CustomAssistChip(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    AssistChip(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        label = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(20.dp)
            )
        },
        shape = RoundedCornerShape(14.dp),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = _DDDADA,
            enabled = true
        )
    )
}