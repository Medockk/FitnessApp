package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker

interface SleepRepository {

    suspend fun getSleepData() : List<SleepTracker>
    suspend fun getSleepDataByDate(date: Int) : List<SleepTracker>
    suspend fun getAlarmClockData() : List<AlarmClockTracker>
    suspend fun getAlarmClockDataByDate(date: Int) : List<AlarmClockTracker>
    suspend fun changeSleepEnabled(sleepTracker: SleepTracker)
    suspend fun changeAlarmEnabled(alarmClockTracker: AlarmClockTracker)

    suspend fun addAlarm(sleepTracker: SleepTracker, alarmClockTracker: AlarmClockTracker)
}