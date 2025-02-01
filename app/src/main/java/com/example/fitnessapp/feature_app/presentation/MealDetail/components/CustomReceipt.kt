package com.example.fitnessapp.feature_app.presentation.MealDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._C150F6
import com.example.fitnessapp.ui.theme._EEA4CE
import com.example.fitnessapp.ui.theme.montserrat40014_1D1617
import com.example.fitnessapp.ui.theme.montserrat40014_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat40014_C150F6

@Composable
fun CustomReceipt(
    step: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    val stepBoxBackground = Brush.linearGradient(listOf(
        _EEA4CE, _C150F6
    ))

    Row(
        modifier = modifier
    ) {
        Text(
            text = "0$step",
            style = montserrat40014_C150F6
        )
        Spacer(Modifier.width(12.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .border(1.dp, stepBoxBackground, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(stepBoxBackground, CircleShape)
                        .padding(5.dp)
                )
            }
            Spacer(Modifier.height(5.dp))
            repeat(5){
                Box(
                    modifier = Modifier
                        .size(1.dp, 10.dp)
                        .background(stepBoxBackground)
                )
                Spacer(Modifier.height(3.dp))
            }
            Spacer(Modifier.height(5.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = "Шаг $step",
                style = montserrat40014_1D1617
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = text,
                style = montserrat40014_B6B4C2
            )
        }
    }
}