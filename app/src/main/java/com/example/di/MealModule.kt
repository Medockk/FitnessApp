package com.example.di

import android.content.Context
import com.example.fitnessapp.feature_app.data.data_source.local.UserMealScheduleDao
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MealModule {

    @Provides
    @Singleton
    fun createMealScheduleDatabase(@ApplicationContext context: Context) : UserMealScheduleDaoDatabase{
        return UserMealScheduleDaoDatabase.createDatabase(context)
    }
    @Provides
    @Singleton
    fun createMealScheduleDao(userMealScheduleDaoDatabase: UserMealScheduleDaoDatabase) : UserMealScheduleDao{
        return userMealScheduleDaoDatabase.userMealScheduleDao
    }
    @Provides
    @Singleton
    fun getMealRepository(userMealScheduleDao: UserMealScheduleDao) : MealRepository{
        return MealRepositoryImpl(userMealScheduleDao)
    }

    //FACTORIES
    @Provides @Singleton
    fun addMealToUserMealScheduleUseCase(mealRepository: MealRepository) : AddMealToUserMealScheduleUseCase{
        return AddMealToUserMealScheduleUseCase(mealRepository)
    }
    @Provides @Singleton
    fun getCategoriesUseCase(mealRepository: MealRepository) : GetCategoriesUseCase{
        return GetCategoriesUseCase(mealRepository)
    }
    @Provides @Singleton
    fun getDietaryRecommendationByIDUseCase(mealRepository: MealRepository) : GetDietaryRecommendationByIDUseCase{
        return GetDietaryRecommendationByIDUseCase(mealRepository)
    }
    @Provides @Singleton
    fun getDietaryRecommendationUseCase(mealRepository: MealRepository) : GetDietaryRecommendationUseCase{
        return GetDietaryRecommendationUseCase(mealRepository)
    }
    @Provides @Singleton
    fun getMealDetailsUseCase(mealRepository: MealRepository) : GetMealDetailsUseCase{
        return GetMealDetailsUseCase(mealRepository)
    }
    @Provides @Singleton
    fun getUserMealScheduleByDateUseCase(mealRepository: MealRepository) : GetUserMealScheduleByDateUseCase{
        return GetUserMealScheduleByDateUseCase(mealRepository)
    }
    @Provides @Singleton
    fun getUserMealScheduleUseCase(mealRepository: MealRepository) : GetUserMealScheduleUseCase{
        return GetUserMealScheduleUseCase(mealRepository)
    }
}