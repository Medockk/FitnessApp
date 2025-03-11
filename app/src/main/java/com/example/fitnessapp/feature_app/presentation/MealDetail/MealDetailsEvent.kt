package com.example.fitnessapp.feature_app.presentation.MealDetail

sealed class MealDetailsEvent {

    data object ResetException : MealDetailsEvent()

    data class AddToBreakfast(val category: String,val mealID: String) : MealDetailsEvent()
}