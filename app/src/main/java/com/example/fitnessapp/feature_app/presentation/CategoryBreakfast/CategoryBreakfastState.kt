package com.example.fitnessapp.feature_app.presentation.CategoryBreakfast

import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation

data class CategoryBreakfastState(

    val searchText: String = "",

    val categories: List<CategoryData> = emptyList(),
    val dietaryRecommendations: List<DietaryRecommendation> = emptyList(),
    val popularMeal: List<DietaryRecommendation> = emptyList(),

    val showMoreInformationAboutMeal: Boolean = false,

    val exception: String = "",
    val showIndicator: Boolean = false,
)
