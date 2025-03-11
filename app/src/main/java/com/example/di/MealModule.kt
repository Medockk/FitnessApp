package com.example.di

import com.example.fitnessapp.feature_app.data.data_source.local.database.UserMealScheduleDaoDatabase
import com.example.fitnessapp.feature_app.data.repository.MealRepositoryImpl
import com.example.fitnessapp.feature_app.domain.repository.MealRepository
import com.example.fitnessapp.feature_app.domain.usecase.Meal.AddMealToUserMealScheduleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetCategoriesUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetDietaryRecommendationByIDUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetDietaryRecommendationUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetMealDetailsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetUserMealScheduleByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Meal.GetUserMealScheduleUseCase
import org.koin.dsl.module

val moduleMeal = module {

    single { UserMealScheduleDaoDatabase.createDatabase(get()) }
    single { get<UserMealScheduleDaoDatabase>().userMealScheduleDao }
    single<MealRepository> {
        MealRepositoryImpl(get())
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
    factory<GetUserMealScheduleByDateUseCase> {
        GetUserMealScheduleByDateUseCase(get())
    }
}