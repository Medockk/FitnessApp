package com.example.fitnessapp.feature_app.presentation.WorkoutTracker.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.axis.Gravity
import co.yml.charts.common.model.AccessibilityConfig
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle

@Composable
fun WorkoutBar(
    list: List<Int>,
    lineColor: List<Color>,
    modifier: Modifier = Modifier
) {

    val dayOfWeek = listOf(
        "Пн","Вт","Ср","Чт","Пт","Сб","Вс"
    )

    val barData = getBarData(list, BarChartType.VERTICAL, lineColor)

    val xAxis = AxisData.Builder()
        .steps(list.size - 1)
        .axisLabelColor(Color.White)
        .axisStepSize(30.dp)
        .axisLineColor(Color.Transparent)
        .backgroundColor(Color.Transparent)
        .axisPosition(Gravity.BOTTOM)
        .labelData { if (it < dayOfWeek.size) dayOfWeek[it] else "" }
        .build()

    val yAxis = AxisData.Builder()
        .steps(list.size)
        .axisStepSize(0.dp)
        .axisPosition(Gravity.LEFT)
        .indicatorLineWidth(0.dp)
        .axisLineThickness(0.dp)
        .axisOffset(-(50).dp)
        .setDataCategoryOptions(DataCategoryOptions(
            isDataCategoryInYAxis = true
        ))
        .labelData {
            val index = (it * (list.max() / list.size)).toString()
            index
        }
        .build()

    BarChart(
        modifier = modifier
            .background(Color.Transparent),
        barChartData = BarChartData(
            chartData = barData,
            xAxisData = xAxis,
            yAxisData = yAxis,
            backgroundColor = Color.Unspecified,
            paddingEnd = 0.dp,
            tapPadding = 0.dp,
            accessibilityConfig = AccessibilityConfig(
                dividerColor = Color.Transparent
            ),
            barStyle = BarStyle(
                cornerRadius = 10.dp,
                isGradientEnabled = true
            )
        )
    )
}

private fun getBarData(list: List<Int>, barChartType: BarChartType, lineColor: List<Color>) : List<BarData>{

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
                    gradientColorList = lineColor,
                    dataCategoryOptions = DataCategoryOptions(isDataCategoryStartFromBottom = true)
                )
            )
        }
        return barDataList
}