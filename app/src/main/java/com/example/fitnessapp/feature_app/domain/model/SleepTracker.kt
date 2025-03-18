package com.example.fitnessapp.feature_app.domain.model

data class SleepTrackerModel(
    val id: Int,
    val userID: String,
    val time: String,
    var enabled: Boolean,
    val lastSleep: String
)

data class AlarmClockTrackerModel(
    val id: Int,
    val userID: String,
    val date: String,
    var enabled: Boolean,
    val time: String
)