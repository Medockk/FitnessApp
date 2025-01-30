package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutSprint(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val sprintNumber: Int,
)
