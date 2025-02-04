package com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutBar(
    list: List<Float>,
    lineColor: Color,
    modifier: Modifier = Modifier
) {

    val dayOfWeek = listOf(
        "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"
    )


    Canvas(
        modifier
    ) {
        val barWidth = (20.dp).toPx()

        list.forEachIndexed { index, i ->
            val height = (i / list.max()) * size.height
            val xOffset = index * barWidth + (20.dp).toPx() * index
            val yOffset = size.height - height

            val linePadding = index * (size.height / 6)

            drawLine(
                color = Color.White,
                start = Offset(0f, linePadding),
                end = Offset(size.width, linePadding),
                strokeWidth = (1.dp).toPx()
            )

            drawRoundRect(
                color = lineColor,
                topLeft = Offset(xOffset, yOffset),
                size = Size(barWidth, height),
                cornerRadius = CornerRadius((10.dp).toPx()),
            )
        }
    }
}