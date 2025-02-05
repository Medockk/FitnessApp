package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule

import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule

data class WorkoutScheduleState(
    val exception: String = "",

    val workoutSchedule: List<WorkoutSchedule> = emptyList(),

    val showIndicator: Boolean = false,
)
