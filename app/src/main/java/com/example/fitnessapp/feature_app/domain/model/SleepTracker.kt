package com.example.fitnessapp.feature_app.domain.model

interface SleepTracker {
    val id: Int
    val userID: String
    val time: String
    var enabled: Boolean
    val lastSleep: String
}

data class SleepTrackerDataImpl(
    override val id: Int,
    override val userID: String,
    override val time: String,
    override var enabled: Boolean,
    override val lastSleep: String
): SleepTracker

interface AlarmClockTracker {
    val id: Int
    val userID: String
    val date: String
    var enabled: Boolean
    val time: String
}

data class AlarmClockTrackerDataImpl(
    override val id: Int,
    override val userID: String,
    override val date: String,
    override var enabled: Boolean,
    override val time: String
) : AlarmClockTracker