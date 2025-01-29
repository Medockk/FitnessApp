package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserWorkoutData(
    val id: Int,
    val userID: String,
    val image: String,
    val title: String,
    val time: String,
    val isTurnOn: Boolean,
)

@Serializable
data class WorkoutData(
    val id: Int,
    val image: String,
    val title: String,
    val workoutCount: String,
)
