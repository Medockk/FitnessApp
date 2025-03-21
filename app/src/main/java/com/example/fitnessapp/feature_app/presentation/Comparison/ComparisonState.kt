package com.example.fitnessapp.feature_app.presentation.Comparison

data class ComparisonState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val firstMount: String = "",
    val secondMount: String = "",
    val monthList: List<String> = listOf(
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь",
    ),

    val showFirstMonthDropDownMenu: Boolean = false,
    val showSecondMonthDropDownMenu: Boolean = false,

    val comparisonResult: List<Int> = emptyList(),
)
