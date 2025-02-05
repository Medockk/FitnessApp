package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.SleepTracker

interface SleepRepository {

    suspend fun getSleepData() : List<SleepTracker>
    suspend fun changeEnabled(sleepTracker: SleepTracker)

    suspend fun addAlarm(sleepTracker: SleepTracker)
}