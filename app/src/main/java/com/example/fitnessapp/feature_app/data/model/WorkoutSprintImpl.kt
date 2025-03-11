package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutSprintImpl(
    override val id: Int,
    override val image: String,
    override val title: String,
    override val description: String,
    override val sprintNumber: Int
) : WorkoutSprint
