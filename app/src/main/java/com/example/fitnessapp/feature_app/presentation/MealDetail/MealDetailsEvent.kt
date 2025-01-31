package com.example.fitnessapp.feature_app.presentation.MealDetail

sealed class MealDetailsEvent {

    data object ResetException : MealDetailsEvent()
}