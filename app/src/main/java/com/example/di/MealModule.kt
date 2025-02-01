package com.example.di

import com.example.fitnessapp.feature_app.data.repository.MealRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.MealRepository
import com.example.fitnessapp.feature_app.domain.usecase.Meal.AddMealToUserMealScheduleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetCategoriesUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetDietaryRecommendationByIDUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetDietaryRecommendationUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetMealDetailsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetUserMealScheduleUseCase
import org.koin.dsl.module

val moduleMeal = module {

    single<MealRepository> {
        MealRepositoryImpl()
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

    factory<GetDietaryRecommendationByIDUseCase> {
        GetDietaryRecommendationByIDUseCase(get())
    }

    factory<GetUserMealScheduleUseCase> {
        GetUserMealScheduleUseCase(get())
    }

    factory<AddMealToUserMealScheduleUseCase> {
        AddMealToUserMealScheduleUseCase(get())
    }
}