package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012White

@Composable
fun CustomWorkoutTextCard(
    text: String,
    modifier: Modifier = Modifier
) {
    val background = Brush.linearGradient(listOf(
        _228F7D, _9CEEDF
    ))

    Card(
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(background, RoundedCornerShape(50.dp), 0.8f)
    ) {
        Text(
            text = text,
            style = montserrat40012White,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}