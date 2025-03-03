package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.MealDetails
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule

interface MealRepository {

    suspend fun getCategories() : List<CategoryData>
    suspend fun getDietaryRecommendation() : List<DietaryRecommendation>

    suspend fun getMealDetails(id: Int) : MealDetails
    suspend fun getDietaryRecommendationByID(id: Int) : DietaryRecommendation

    suspend fun getUserMealSchedule() : List<UserMealSchedule>
    suspend fun addMealToUserMealSchedule(meal: DietaryRecommendation)
    suspend fun getUserMealScheduleByDate(date: Int) : List<UserMealSchedule>
}