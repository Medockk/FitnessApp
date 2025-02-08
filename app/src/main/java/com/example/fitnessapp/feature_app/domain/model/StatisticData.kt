package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class StatisticData(
    val id: Int,
    val date: String,
    val userID: String,
    val type: String,
    val value: String
)