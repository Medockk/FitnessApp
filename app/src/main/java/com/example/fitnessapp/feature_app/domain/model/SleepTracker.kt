package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SleepTracker(
    val id: Int,
    val userID: String,
    val time: String,
    val enabled: Boolean,
    val lastSleep: String,
)

@Serializable
data class AlarmClockTracker(
    val id: Int,
    val userID: String,
    val date: String,
    val enabled: Boolean,
    val time: String
)
