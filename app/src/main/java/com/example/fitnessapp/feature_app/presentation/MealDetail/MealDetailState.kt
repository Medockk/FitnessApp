package com.example.fitnessapp.feature_app.presentation.MealDetail

import com.example.fitnessapp.feature_app.domain.model.MealDetails

data class MealDetailState(
    val exception: String = "",

    val details: MealDetails = MealDetails(0,"","",""),
    val receipt: List<String> = emptyList(),
    val ingredientCount: Int = 0,
    val ingredients: List<String> = emptyList(),

    val nutrition : List<String> = emptyList(),
    val showIndicator: Boolean = false,
)
