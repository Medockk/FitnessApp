package com.example.fitnessapp.feature_app.presentation.ActivityTracker.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2

@Composable
fun ActivityBarChart(
    barChartList: List<Float>,
    modifier: Modifier = Modifier
) {

    val dayWeek = listOf(
        "Пон", "Вто", "Сре", "Чет", "Пят", "Суб", "Вос"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Canvas(modifier.background(Color.Transparent)) {

            val barWidth = 20.dp.toPx()

            barChartList.forEachIndexed { index, i ->
                val height = (i / barChartList.max()) * size.height
                val x = (barWidth * index) + index * 20.dp.toPx()
                val y = size.height - height

                drawRoundRect(
                    color = _F7F8F8,
                    topLeft = Offset(x, 0f),
                    size = Size(barWidth, size.height),
                    cornerRadius = CornerRadius(20.dp.toPx()),
                )

                drawRoundRect(
                    brush = if (index % 2 == 0) {
                        Brush.linearGradient(listOf(_228F7D, _9CEEDF))
                    } else {
                        Brush.linearGradient(
                            listOf(
                                _9CEEDF, _9CEEDF
                            )
                        )
                    },
                    topLeft = Offset(x, y),
                    size = Size(barWidth, height),
                    cornerRadius = CornerRadius(20.dp.toPx())
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (barChartList.size > dayWeek.size){
                repeat(dayWeek.size) {
                    Text(
                        text = dayWeek[it],
                        style = montserrat40012_B6B4C2
                    )
                    Spacer(Modifier.width(15.dp))
                }
            }else{
                repeat(barChartList.size) {
                    Text(
                        text = dayWeek[it],
                        style = montserrat40012_B6B4C2
                    )
                    Spacer(Modifier.width(15.dp))
                }
            }
        }
    }
}