package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BodyMassIndexData(
    val id: Int,
    val value: Float,
    val userID: String,
)
