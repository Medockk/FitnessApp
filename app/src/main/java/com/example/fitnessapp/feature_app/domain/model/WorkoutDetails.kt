package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDetails(
    val id: Int,
    val workoutSprintID: Int,
    val videoUrl: String,
    val description: String
)
