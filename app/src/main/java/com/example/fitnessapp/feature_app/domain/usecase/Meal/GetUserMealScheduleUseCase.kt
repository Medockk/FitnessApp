package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class GetUserMealScheduleUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke() : List<UserMealSchedule>{
        return mealRepository.getUserMealSchedule()
    }
}