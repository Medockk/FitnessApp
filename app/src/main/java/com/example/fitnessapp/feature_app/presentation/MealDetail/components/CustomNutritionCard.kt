package com.example.fitnessapp.feature_app.presentation.MealDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.fitnessapp.ui.theme._00F0FF
import com.example.fitnessapp.ui.theme._00FF66
import com.example.fitnessapp.ui.theme.montserrat40010_ADA4A5

@Composable
fun CustomNutritionCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    val background = Brush.linearGradient(listOf(
        _00F0FF, _00FF66
    ))

    Card(
        modifier = modifier
            .background(background, RoundedCornerShape(12.dp), 0.8f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = title,
                style = montserrat40010_ADA4A5
            )
        }
    }
}