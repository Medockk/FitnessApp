package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.WorkoutDetails
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDetailsImpl(
    override val id: Int,
    override val workoutSprintID: Int,
    override val videoUrl: String,
    override val description: String
) : WorkoutDetails
