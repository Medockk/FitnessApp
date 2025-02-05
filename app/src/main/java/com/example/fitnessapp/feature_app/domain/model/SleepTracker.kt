package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SleepTracker(
    val id: Int,
    val userID: String,
    val icon: String,
    val title: String,
    val time: String,
    val enabled: Boolean,
    val lastSleep: String,
)
