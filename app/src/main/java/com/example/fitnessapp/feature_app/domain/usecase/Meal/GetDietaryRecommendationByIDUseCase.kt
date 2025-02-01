package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class GetDietaryRecommendationByIDUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke(id: Int) : DietaryRecommendation{
        return mealRepository.getDietaryRecommendationByID(id)
    }
}