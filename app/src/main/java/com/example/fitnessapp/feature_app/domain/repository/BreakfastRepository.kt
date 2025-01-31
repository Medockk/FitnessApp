package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.MealDetails

interface BreakfastRepository {

    suspend fun getCategories() : List<CategoryData>
    suspend fun getDietaryRecommendation() : List<DietaryRecommendation>

    suspend fun getMealDetails(id: Int) : MealDetails
}