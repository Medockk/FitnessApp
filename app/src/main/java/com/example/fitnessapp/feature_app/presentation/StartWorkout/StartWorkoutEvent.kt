package com.example.fitnessapp.feature_app.presentation.StartWorkout

sealed class StartWorkoutEvent {

    data object ResetException : StartWorkoutEvent()
}