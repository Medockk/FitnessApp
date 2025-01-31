package com.example.fitnessapp.feature_app.domain.usecase.Breakfast

import com.example.fitnessapp.feature_app.domain.model.MealDetails
import com.example.fitnessapp.feature_app.domain.repository.BreakfastRepository

class GetMealDetailsUseCase(
    private val breakfastRepository: BreakfastRepository
) {

    suspend operator fun invoke(id: Int) :MealDetails{
        return breakfastRepository.getMealDetails(id)
    }
}