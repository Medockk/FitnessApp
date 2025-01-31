package com.example.di

import com.example.fitnessapp.feature_app.data.repository.BreakfastRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.BreakfastRepository
import com.example.fitnessapp.feature_app.domain.usecase.Breakfast.GetCategoriesUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Breakfast.GetDietaryRecommendationUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Breakfast.GetMealDetailsUseCase
import org.koin.dsl.module

val moduleBreakfast = module {

    single<BreakfastRepository> {
        BreakfastRepositoryImpl()
    }

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCase(get())
    }

    factory<GetDietaryRecommendationUseCase> {
        GetDietaryRecommendationUseCase(get())
    }

    factory<GetMealDetailsUseCase> {
        GetMealDetailsUseCase(get())
    }
}