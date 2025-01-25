package com.example.fitnessapp.feature_app.presentation.Home

sealed class HomeEvent {

    data object ResetException : HomeEvent()
}