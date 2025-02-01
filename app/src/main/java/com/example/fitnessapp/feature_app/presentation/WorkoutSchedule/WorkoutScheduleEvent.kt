package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule

sealed class WorkoutScheduleEvent {

    data object ResetException : WorkoutScheduleEvent()
}