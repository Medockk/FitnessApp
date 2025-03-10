package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class AddMealToUserMealScheduleUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke(category: String, mealID: String){
        mealRepository.addMealToUserMealSchedule(category, mealID)
    }
}