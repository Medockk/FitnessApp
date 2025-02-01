package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.model.MealDetails
import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class GetMealDetailsUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke(id: Int) :MealDetails{
        return mealRepository.getMealDetails(id)
    }
}