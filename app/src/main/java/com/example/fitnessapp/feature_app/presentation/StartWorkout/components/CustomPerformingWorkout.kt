package com.example.fitnessapp.feature_app.presentation.StartWorkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40014_1D1617
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40014_228F7D

@Composable
fun CustomPerformingWorkout(
    title: String,
    description: String,
    workoutNumber: Int,
    modifier: Modifier = Modifier
) {
    val background = Brush.linearGradient(listOf(
        _228F7D, _9CEEDF
    ))

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = if (workoutNumber < 10) "0$workoutNumber" else workoutNumber.toString(),
            style = montserrat40014_228F7D
        )
        Spacer(Modifier.width(10.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .border(1.dp, background, CircleShape),
                contentAlignment = Alignment.Center
            ){
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(background, CircleShape)
                )
            }
            repeat(7){
                Box(Modifier.size(1.dp,5.dp).background(background))
                Spacer(Modifier.height(5.dp))
            }
        }
        Spacer(Modifier.width(15.dp))
        Column {
            Text(
                text = title,
                style = montserrat40014_1D1617
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = description,
                style = montserrat40012_B6B4C2
            )
        }
    }
}