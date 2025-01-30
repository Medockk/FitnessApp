package com.example.fitnessapp.feature_app.presentation.CategoryBreakfast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme.montserrat40012_1D1617

@Composable
fun CategoryCard(
    category: CategoryData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val background = Brush.linearGradient(listOf(
        _228F7D, _9CEEDF
    ))

    Card(
        modifier = modifier
            .background(background, RoundedCornerShape(16.dp), 0.2f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = category.image,
                    contentDescription = "category",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(40.dp, 30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = category.title,
                style = montserrat40012_1D1617
            )
        }
    }
}