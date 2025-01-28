package com.example.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.Gravity
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import com.example.fitnessapp.ui.theme._228F7D

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    val list = listOf(0, 15, 20, 74, 54, 60)
    BarChart(
        barChartList = list,
        _228F7D,
        axisStepSize = 10.dp,
        showLabel = true,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    )
}

@Composable
fun BarChart(
    barChartList: List<Int>,
    lineColor: Color,
    colorList: List<Color> = listOf(),
    axisStepSize: Dp = 20.dp,
    showLabel: Boolean = false,
    modifier: Modifier = Modifier
) {

    val barDataList = getBarData(barChartList,
        lineColor, colorList,
        barChartType = BarChartType.VERTICAL)

    val xAxisData = AxisData.Builder()
        .steps(barChartList.size - 1)
        .axisStepSize(axisStepSize)
        .backgroundColor(Color.Transparent)
        .axisLineColor(Color.Transparent)
        .axisPosition(Gravity.BOTTOM)
        .build()

    val barChart = BarChartData(
        chartData = barDataList,
        xAxisData = xAxisData,
        backgroundColor = Color.Transparent,
        showYAxis = showLabel,
        barStyle = BarStyle(
            cornerRadius = 10.dp,
            paddingBetweenBars = axisStepSize
        ),
    )

    co.yml.charts.ui.barchart.BarChart(
        modifier = modifier,
        barChartData = barChart
    )
}

fun getBarData(list: List<Int>, lineColor: Color, colorList: List<Color>, barChartType: BarChartType): List<BarData> {


    val barDataList = arrayListOf<BarData>()
    for (index in list.indices) {
        val point = when (barChartType) {
            BarChartType.VERTICAL -> {
                Point(
                    index.toFloat(),
                    list[index].toFloat()
                )
            }

            BarChartType.HORIZONTAL -> {
                Point(
                    list[index].toFloat(),
                    index.toFloat()
                )
            }
        }

        barDataList.add(
            BarData(
                point = point,
                color = lineColor,
                gradientColorList = colorList
            )
        )
    }
    return barDataList
}