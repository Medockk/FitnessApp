package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HeartRate(
    val id: Int,
    val userID: String,
    val heartRateList: String,
)
