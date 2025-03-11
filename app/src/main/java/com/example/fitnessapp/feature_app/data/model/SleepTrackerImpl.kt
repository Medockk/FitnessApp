package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import kotlinx.serialization.Serializable

@Serializable
data class SleepTrackerImpl(
    override val id: Int,
    override val userID: String,
    override val time: String,
    override var enabled: Boolean,
    override val lastSleep: String
) : SleepTracker

@Serializable
data class AlarmClockTrackerImpl(
    override val id: Int,
    override val userID: String,
    override val date: String,
    override var enabled: Boolean,
    override val time: String
) : AlarmClockTracker