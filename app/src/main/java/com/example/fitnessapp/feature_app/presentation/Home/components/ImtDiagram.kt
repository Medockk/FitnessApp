package com.example.fitnessapp.feature_app.presentation.Home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    ImtDiagram(
        pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice(
                    label = "",
                    value = 20.1f,
                    color = Color.Red,{""}),
                PieChartData.Slice(
                    "nothing",
                    79.9f,
                    Color.White,{""}
                )
            ),
            plotType = PlotType.Pie
        ),
    )
}

@Composable
fun ImtDiagram(
    pieChartData: PieChartData,
    pieChartConfig: PieChartConfig = PieChartConfig(),
    modifier: Modifier = Modifier
) {

    PieChart(
        modifier = modifier,
        pieChartData = pieChartData,
        pieChartConfig = pieChartConfig
    )
}