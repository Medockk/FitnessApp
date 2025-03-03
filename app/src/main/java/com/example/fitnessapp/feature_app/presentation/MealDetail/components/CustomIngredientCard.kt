package com.example.fitnessapp.feature_app.presentation.MealDetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_1D1617

@Composable
fun CustomIngredientCard(
    ingredient: String,
    icon: ImageVector,
    ingredientCount: String,
    modifier: Modifier = Modifier
) {
    Column {
        Card(
            colors = CardDefaults.cardColors(containerColor = _F7F8F8),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
        ) {
            Icon(
                imageVector = icon,
                contentDescription = ingredient,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(20.dp)
                    .size(45.dp)
            )
        }
        Text(
            text = ingredient,
            style = montserrat40012_1D1617
        )
        Text(
            text = ingredientCount,
            style = montserrat40010_B6B4C2
        )
    }
}