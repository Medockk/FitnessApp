package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class GetDietaryRecommendationUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke() : List<DietaryRecommendation>{
        return mealRepository.getDietaryRecommendation()
    }
}