package com.example.fitnessapp.feature_app.presentation.WorkoutDetail

import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint

data class WorkoutDetailState(
    val exception: String = "",

    val sprint1: List<WorkoutSprint> = emptyList(),
    val sprint2: List<WorkoutSprint> = emptyList(),
)
