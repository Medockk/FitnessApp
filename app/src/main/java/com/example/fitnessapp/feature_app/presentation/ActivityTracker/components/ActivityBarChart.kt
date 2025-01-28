package com.example.fitnessapp.feature_app.presentation.ActivityTracker.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme._A8E3D9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    ActivityBarChart(
        listOf(
            0.1f, 0.75f, 0.65f, 0.78f, 0.51f
        ),
        _A8E3D9
    )
}

@Composable
fun ActivityBarChart(
    barChartList: List<Float>,
    lineColor: Color,
    padding: Dp = 0.dp,
    modifier: Modifier = Modifier
) {

    val dayWeek = listOf(
        "Пон", "Вто","Сре","Чет","Пят","Суб","Вос"
    )
    val colorList = listOf(
        _228F7D, _9CEEDF
    )

    val barDataList = getBarChartData(
        barChartList,
        barChartType = BarChartType.VERTICAL,
        lineColor = lineColor,
        gradientList = colorList,
        DataCategoryOptions(),
    )

    val xAxisData = AxisData.Builder()
        .steps(barChartList.size - 1)
        .axisLineColor(Color.Transparent)
        .axisStepSize(15.dp)
        .startPadding(padding)
        .startDrawPadding(padding)
        .labelData { dayWeek[it] }
        .build()

    val barChartData = BarChartData(
        chartData = barDataList,
        xAxisData = xAxisData,
        showYAxis = false,
        backgroundColor = Color.White,
        barStyle = BarStyle(
            cornerRadius = 10.dp,
            barWidth = 22.dp
        ),
    )

    BarChart(
        modifier = modifier,
        barChartData = barChartData
    )
}

private fun getBarChartData(
    listBar: List<Float>,
    barChartType: BarChartType,
    lineColor: Color,
    gradientList: List<Color>,
    dataCategoryOptions: DataCategoryOptions
): List<BarData> {
    val list = arrayListOf<BarData>()
    for (index in listBar.indices) {
        val point = when (barChartType) {
            BarChartType.VERTICAL -> {
                Point(
                    index.toFloat(),
                    listBar[index]
                )
            }

            BarChartType.HORIZONTAL -> {
                Point(
                    listBar[index],
                    index.toFloat()
                )
            }
        }

        list.add(
            BarData(
                point = point,
                color = lineColor,
                dataCategoryOptions = dataCategoryOptions,
                label = "Bar$index",
                gradientColorList = gradientList
            )
        )
    }
    return list
}