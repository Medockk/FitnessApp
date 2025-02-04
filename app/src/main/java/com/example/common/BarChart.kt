package com.example.common

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._A8E3D9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    val list = listOf(0f, 15f, 20f, 74f, 54f, 60f)
    BarChart(
        barChartList = list,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    )
}

@Composable
fun BarChart(
    barChartList: List<Float>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            Canvas(
                   modifier
                       .height(120.dp)
                       .background(Color.Transparent)
            ) {

                val barWidth = (20.dp).toPx()

                barChartList.forEachIndexed { index, i ->
                    val height = (i / barChartList.max()) * size.height
                    val x = index * barWidth + (5.dp).toPx() * index
                    val y = size.height - height

                    drawRoundRect(
                        color = _A8E3D9,
                        cornerRadius = CornerRadius((10.dp).toPx()),
                        size = Size(barWidth, height),
                        topLeft = Offset(x, y)
                    )
                    Log.e("index", "$x $y\n$height $index")
                }
            }
        }
        item {
            Box(Modifier.size(10.dp))
        }
    }
}