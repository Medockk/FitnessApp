package com.example.fitnessapp.feature_app.presentation.StartWorkout

data class StartWorkoutState(
    val exception: String = "",

    val workoutDescription: String = "",

    val showIndicator: Boolean = false,
)