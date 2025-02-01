package com.example.fitnessapp.feature_app.presentation.MealSchedule

sealed class MealScheduleEvent {

    data object ResetException : MealScheduleEvent()
}