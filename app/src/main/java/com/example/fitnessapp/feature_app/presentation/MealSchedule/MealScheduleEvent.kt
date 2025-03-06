package com.example.fitnessapp.feature_app.presentation.MealSchedule

import java.time.LocalDate

sealed class MealScheduleEvent {

    data object ResetException : MealScheduleEvent()
    data class MonthClick(val value: LocalDate) : MealScheduleEvent()
}