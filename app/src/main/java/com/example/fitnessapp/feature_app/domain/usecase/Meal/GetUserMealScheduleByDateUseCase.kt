package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class GetUserMealScheduleByDateUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke(year: Int, month: Int,day: Int) : List<UserMealSchedule>{
        return mealRepository.getUserMealScheduleByDate(year, month, day)
    }
}