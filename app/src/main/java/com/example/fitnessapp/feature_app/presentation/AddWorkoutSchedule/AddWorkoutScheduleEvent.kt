package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule

sealed class AddWorkoutScheduleEvent {

    data object ResetException : AddWorkoutScheduleEvent()

    data object AddWorkout : AddWorkoutScheduleEvent()
}