package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker

interface SleepRepository {

    suspend fun getSleepData() : List<SleepTracker>
    suspend fun getSleepDataByDate(year: Int, month: Int, day: Int) : List<SleepTracker>
    suspend fun getAlarmClockData() : List<AlarmClockTracker>
    suspend fun getAlarmClockDataByDate(year: Int, month: Int, day: Int) : List<AlarmClockTracker>
    suspend fun changeSleepEnabled(sleepTrackerId: Int, sleepTrackerEnabled: Boolean)
    suspend fun changeAlarmEnabled(alarmClockTrackerEnabled: Boolean, alarmClockTrackerId: Int)

    suspend fun addAlarm(sleepTrackerTime: String, alarmClockTrackerTime: String)
}