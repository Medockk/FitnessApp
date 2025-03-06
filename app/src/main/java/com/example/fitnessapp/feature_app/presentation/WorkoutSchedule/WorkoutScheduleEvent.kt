package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule

import java.time.LocalDate

sealed class WorkoutScheduleEvent {

    data object ResetException : WorkoutScheduleEvent()
    data class MonthClick(val value: LocalDate) : WorkoutScheduleEvent()
}