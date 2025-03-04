package com.example.fitnessapp.feature_app.presentation.MealSchedule

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import com.example.fitnessapp.feature_app.domain.model.UserMealSchedule
import java.time.LocalDate

data class MealScheduleState(
    val exception: String = "",

    val mealSchedule: List<UserMealSchedule> = emptyList(),
    val dietaryRecommendation: List<DietaryRecommendation> = emptyList(),
    val caloriesSum: Int = 0,
    val proteinSum: Int = 0,
    val fatSum: Int = 0,
    val carboSum: Int = 0,

    val breakfastMeal: List<MealScheduleItem> = emptyList(),
    val breakfastCalories: String = "",
    val launchMeal: List<MealScheduleItem> = emptyList(),
    val lunchCalories: String = "",
    val afternoonMeal: List<MealScheduleItem> = emptyList(),
    val afternoonCalories: String = "",
    val dinnerMeal: List<MealScheduleItem> = emptyList(),
    val dinnerCalories: String = "",

    val showIndicator: Boolean = false,
    val currentDay: Int = if (LocalDate.now().month.value==2 && LocalDate.now().isLeapYear){
        LocalDate.now().dayOfMonth - 1
    }else{
        LocalDate.now().dayOfMonth
    },
)
