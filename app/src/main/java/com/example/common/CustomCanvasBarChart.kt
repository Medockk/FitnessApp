package com.example.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCanvasBarChart(
    list: List<Float>,
    height: Dp,
    lineColor: Color,
    xAxisLineColor: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier
) {

    val dayOfWeek = listOf(
        "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"
    )


    Column{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Canvas(
                Modifier.height(height).weight(1f)
            ) {
                val barWidth = (20.dp).toPx()

                list.forEachIndexed { index, i ->
                    val height = (i / list.max()) * size.height
                    val xOffset = index * barWidth + (20.dp).toPx() * index
                    val yOffset = size.height - height

                    val linePadding = index * (size.height / 6)

                    drawLine(
                        color = xAxisLineColor,
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
            Spacer(Modifier.width(5.dp))
            LazyColumn(
                reverseLayout = true,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.height(height+10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(6){
                    item {
                        Text(
                            text = "${it * 20}%",
                            style = textStyle
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(dayOfWeek.size){
                Text(
                    text = dayOfWeek[it],
                    style = textStyle
                )
            }
        }
    }
}