package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import kotlinx.serialization.Serializable

@Serializable
data class UserWorkoutDataImpl(
    override val id: Int,
    override val userID: String,
    override val image: String,
    override val title: String,
    override val time: String,
    override var isTurnOn: Boolean
) : UserWorkoutData
