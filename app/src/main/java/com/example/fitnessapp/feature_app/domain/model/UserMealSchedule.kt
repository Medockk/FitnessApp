package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserMealSchedule(
    val id: Int,
    val userID: String,
    val category: String,
    val mealID: Int,
    val time: String,
    val date: String
){
    companion object{
        const val categoryBreakfast = "breakfast"
        const val categoryLunch = "lunch"
        const val categoryAfternoonSnack = "afternoon"
        const val categoryDinner = "dinner"
    }
}
