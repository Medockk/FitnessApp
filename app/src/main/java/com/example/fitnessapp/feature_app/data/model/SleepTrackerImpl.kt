package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel
import kotlinx.serialization.Serializable

@Serializable
data class SleepTrackerModelDto(
    val id: Int,
    val userID: String,
    val time: String,
    var enabled: Boolean,
    val lastSleep: String
){
    fun toSleepTracker() : SleepTrackerModel{
        return SleepTrackerModel(id, userID, time, enabled, lastSleep)
    }
}

@Serializable
data class AlarmClockTrackerModelDto(
    val id: Int,
    val userID: String,
    val date: String,
    var enabled: Boolean,
    val time: String
){
    fun toAlarmClockTracker() : AlarmClockTrackerModel{
        return AlarmClockTrackerModel(id, userID, date, enabled, time)
    }
}