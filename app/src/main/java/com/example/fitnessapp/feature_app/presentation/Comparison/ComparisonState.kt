package com.example.fitnessapp.feature_app.presentation.Comparison

data class ComparisonState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val firstMount: String = "",
    val secondMount: String = "",

    val comparisonResult: List<Int> = emptyList(),
)
