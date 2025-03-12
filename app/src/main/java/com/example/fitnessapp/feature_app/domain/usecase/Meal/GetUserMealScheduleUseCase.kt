package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import com.example.fitnessapp.feature_app.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow

class GetUserMealScheduleUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke() : Flow<NetworkResult<List<UserMealSchedule>>> {
        return mealRepository.getUserMealSchedule()
    }
}