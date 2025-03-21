package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.MealDetails
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    suspend fun getCategories() : List<CategoryData>
    suspend fun getDietaryRecommendation() : List<DietaryRecommendation>

    suspend fun getMealDetails(id: Int) : MealDetails
    suspend fun getDietaryRecommendationByID(id: Int) : DietaryRecommendation

    suspend fun getUserMealSchedule() : Flow<NetworkResult<List<UserMealSchedule>>>
    suspend fun addMealToUserMealSchedule(category: String, mealID: String)
    suspend fun getUserMealScheduleByDate(year: Int, month: Int,day: Int) : List<UserMealSchedule>
}