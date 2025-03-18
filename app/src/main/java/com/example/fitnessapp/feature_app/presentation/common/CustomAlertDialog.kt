package com.example.fitnessapp.feature_app.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_ADA4A5
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70036_52B09F

@Composable
fun CustomAlertDialog(
    description: String,
    title: String = "Ошибка!",
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
) {
    AlertDialog(
        modifier = modifier
            .testTag("dialog"),
        onDismissRequest = onDismissClick,
        confirmButton = {},
        icon = {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(_228F7D, CircleShape),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        },
        title = {
            Text(
                text = title,
                style = montserrat70036_52B09F
            )
        },
        text = {
            Text(
                text = description,
                style = montserrat50014_ADA4A5,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        shape = RoundedCornerShape(20.dp),
        containerColor = Color.White,
        tonalElevation = 5.dp
    )
}