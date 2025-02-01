package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutSchedule(
    val id: Int,
    val time: String,
    val title: String,
    val userID: String
)
