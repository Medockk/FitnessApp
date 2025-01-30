package com.example.fitnessapp.feature_app.domain.usecase.Breakfast

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.repository.BreakfastRepository

class GetDietaryRecommendationUseCase(
    private val breakfastRepository: BreakfastRepository
) {

    suspend operator fun invoke() : List<DietaryRecommendation>{
        return breakfastRepository.getDietaryRecommendation()
    }
}