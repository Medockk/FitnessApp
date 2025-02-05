package com.example.fitnessapp.feature_app.presentation.WorkoutTracker

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData

data class WorkoutTrackerState(

    val allBodyWorkout: Boolean = false,
    val upperPartWorkout: Boolean = false,

    val workoutBar: List<Float> = emptyList(),
    val userWorkoutList: List<UserWorkoutData> = emptyList(),
    val workoutList: List<WorkoutData> = emptyList(),

    val exception: String = "",

    val showIndicator: Boolean = false,
)
