package com.example.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._228F7D

@Composable
fun CustomFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = (LocalConfiguration.current.screenHeightDp / 20).dp, end = 30.dp
            ),
        contentAlignment = Alignment.BottomEnd
    ){
        FloatingActionButton(
            onClick = onClick,
            shape = CircleShape,
            containerColor = _228F7D,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = modifier,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.White,
                contentDescription = "add"
            )
        }
    }
}