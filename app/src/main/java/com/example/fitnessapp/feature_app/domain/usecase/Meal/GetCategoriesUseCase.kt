package com.example.fitnessapp.feature_app.domain.usecase.Meal

import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.repository.MealRepository

class GetCategoriesUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke() : List<CategoryData>{
        return mealRepository.getCategories()
    }
}