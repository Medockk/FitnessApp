package com.example.fitnessapp.feature_app.presentation.CompareResult

data class CompareResultState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val middleProgress: String = "Хорошо",
    val floatMiddleProgress: Float = 0.7f,

    val statisticList: List<Float> = listOf(5f, 3f, 4f, 1f, 1f, 2f, 5f),
)
