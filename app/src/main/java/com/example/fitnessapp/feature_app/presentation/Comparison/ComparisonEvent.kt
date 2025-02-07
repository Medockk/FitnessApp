package com.example.fitnessapp.feature_app.presentation.Comparison

sealed class ComparisonEvent {

    data object ResetException : ComparisonEvent()
    data object ComparisonClick : ComparisonEvent()

    data class EnterFirstMonth(val value: String) : ComparisonEvent()
    data class EnterSecondMonth(val value: String) : ComparisonEvent()

    data object ChangeFirstMonthShowDropdownMenuState : ComparisonEvent()
    data object ChangeSecondMonthShowDropdownMenuState : ComparisonEvent()
}