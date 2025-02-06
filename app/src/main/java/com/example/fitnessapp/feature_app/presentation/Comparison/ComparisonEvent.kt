package com.example.fitnessapp.feature_app.presentation.Comparison

sealed class ComparisonEvent {

    data object ResetException : ComparisonEvent()
    data object ComparisonClick : ComparisonEvent()
}