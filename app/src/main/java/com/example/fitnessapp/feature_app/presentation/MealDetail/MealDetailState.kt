package com.example.fitnessapp.feature_app.presentation.MealDetail

data class MealDetailState(
    val exception: String = "",

    val ingredients: List<String> = emptyList(),
    val nutrition : List<String> = emptyList(),
)
