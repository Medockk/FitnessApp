package com.example.fitnessapp.feature_app.presentation.CategoryBreakfast

sealed class CategoryBreakfastEvent {

    data class EnterSearchText(val value: String) : CategoryBreakfastEvent()
    data object ResetException : CategoryBreakfastEvent()
}