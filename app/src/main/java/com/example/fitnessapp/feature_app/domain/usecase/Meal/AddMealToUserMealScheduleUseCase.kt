package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class AddMealToUserMealScheduleUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke(meal: DietaryRecommendation){
        mealRepository.addMealToUserMealSchedule(meal)
    }
}