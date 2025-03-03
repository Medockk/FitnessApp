package com.example.fitnessapp.feature_app.presentation.WorkoutDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.presentation.ui.theme._B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2

@Composable
fun CustomWorkoutInfo(
    backgroundBrush: Brush,
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier
            .background(backgroundBrush, RoundedCornerShape(16.dp), 0.8f),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = title,
                style = montserrat40012_B6B4C2
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = description,
                style = montserrat40010_B6B4C2
            )
            Spacer(Modifier.width(10.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = _B6B4C2
            )
        }
    }
}