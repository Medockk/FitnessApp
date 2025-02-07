package com.example.fitnessapp.feature_app.presentation.CompareResult

sealed class CompareResultEvent {

    data object ResetException : CompareResultEvent()

}