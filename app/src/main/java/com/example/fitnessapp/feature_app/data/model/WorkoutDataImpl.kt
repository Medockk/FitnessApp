package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDataImpl(
    override val id: Int,
    override val image: String,
    override val title: String,
    override val workoutCount: String,
    override val itemCount: String
) : WorkoutData
