package com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.presentation.common.CustomGreenButton
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_1D1617

@Composable
fun DietaryCard(
    dietary: DietaryRecommendation,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val background = Brush.linearGradient(listOf(
        _228F7D, _9CEEDF
    ))

    Card(
        modifier = modifier
            .background(background, RoundedCornerShape(20.dp), 0.2f),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
        ) {
            AsyncImage(
                model = dietary.image,
                contentDescription = "dietary image",
                modifier = Modifier
                    .padding(1.dp)
                    .size(135.dp, 90.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.height(15.dp))
            Text(
                text = dietary.title,
                style = montserrat50014_1D1617
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = dietary.description,
                style = montserrat40012_B6B4C2
            )
            Spacer(Modifier.height(15.dp))
            CustomGreenButton(
                text = "Смотреть",
                onClick = onClick
            )
        }
    }
}