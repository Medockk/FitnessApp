package com.example.fitnessapp.feature_app.domain.usecase.Breakfast

import com.example.fitnessapp.feature_app.domain.model.CategoryData
import com.example.fitnessapp.feature_app.domain.repository.BreakfastRepository

class GetCategoriesUseCase(
    private val breakfastRepository: BreakfastRepository
) {

    suspend operator fun invoke() : List<CategoryData>{
        return breakfastRepository.getCategories()
    }
}