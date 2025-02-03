package com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50014_1D1617

@Composable
fun PopularMealCard(
    popularMeal: DietaryRecommendation,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = popularMeal.image,
                contentDescription = "popular meal",
                modifier = Modifier
                    .padding(1.dp)
                    .size(45.dp),
                contentScale = ContentScale.Fit
            )
            Column {
                Text(
                    text = popularMeal.title,
                    style = montserrat50014_1D1617
                )
                Text(
                    text = popularMeal.description,
                    style = montserrat40012_B6B4C2
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.workout_next_icon),
                contentDescription = null,
                tint = _228F7D,
                modifier = Modifier
                    .clickable { onClick() }
            )
        }
    }
}