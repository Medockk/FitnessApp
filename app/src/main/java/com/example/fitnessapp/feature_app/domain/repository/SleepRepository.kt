package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel

interface SleepRepository {

    suspend fun getSleepData() : List<SleepTrackerModel>
    suspend fun getSleepDataByDate(year: Int, month: Int, day: Int) : List<SleepTrackerModel>
    suspend fun getAlarmClockData() : List<AlarmClockTrackerModel>
    suspend fun getAlarmClockDataByDate(year: Int, month: Int, day: Int) : List<AlarmClockTrackerModel>
    suspend fun changeSleepEnabled(sleepTrackerId: Int, sleepTrackerEnabled: Boolean)
    suspend fun changeAlarmEnabled(alarmClockTrackerId: Int, alarmClockTrackerEnabled: Boolean)

    suspend fun addAlarm(sleepTrackerTime: String, alarmClockTrackerTime: String)
}