package com.example.fitnessapp.feature_app.domain.model

interface SleepTracker {
    val id: Int
    val userID: String
    val time: String
    var enabled: Boolean
    val lastSleep: String
}

interface AlarmClockTracker {
    val id: Int
    val userID: String
    val date: String
    var enabled: Boolean
    val time: String
}
