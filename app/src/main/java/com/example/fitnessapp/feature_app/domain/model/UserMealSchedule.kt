package com.example.fitnessapp.feature_app.domain.model

interface UserMealSchedule {
    val id: Int
    val userID: String
    val category: String
    val mealID: Int
    val time: String
    val date: String

    companion object{
        const val CATEGORY_BREAKFAST = "breakfast"
        const val CATEGORY_LUNCH = "lunch"
        const val CATEGORY_AFTERNOON_SNACK = "afternoon"
        const val CATEGORY_DINNER = "dinner"
    }
}
