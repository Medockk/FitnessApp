package com.example.fitnessapp.feature_app.presentation.MealSchedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50012Black

@Composable
fun CustomProductMassCard(
    item: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    val background = Brush.linearGradient(listOf(
        _228F7D, _9CEEDF
    ))
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (item){
                        0 -> "Каллории"
                        1 -> "Протеин"
                        2 -> "Жиры"
                        else -> "Углнводы"
                    },
                    style = montserrat50012Black
                )
                Icon(
                    imageVector = when (item){
                        0 -> ImageVector.vectorResource(R.drawable.calories_icon)
                        1 -> ImageVector.vectorResource(R.drawable.proteins_icon)
                        2 -> ImageVector.vectorResource(R.drawable.fat_icon)
                        else -> ImageVector.vectorResource(R.drawable.carbo_icon)
                    },
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = value,
                    style = montserrat40010_B6B4C2
                )
            }
            Spacer(Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(_F7F8F8, RoundedCornerShape(100.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f / value.toFloat() + value.toFloat() / 100f)
                        .height(10.dp)
                        .background(background, RoundedCornerShape(100.dp))
                )
            }
        }
    }
}