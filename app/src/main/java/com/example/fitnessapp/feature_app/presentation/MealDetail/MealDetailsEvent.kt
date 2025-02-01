package com.example.fitnessapp.feature_app.presentation.MealDetail

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation

sealed class MealDetailsEvent {

    data object ResetException : MealDetailsEvent()

    data class AddToBreakfast(val meal: DietaryRecommendation) : MealDetailsEvent()
}