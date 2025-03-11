package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class AddAlarmUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(sleepTrackerTime: String, alarmClockTrackerTime: String) {
        sleepRepository.addAlarm(sleepTrackerTime, alarmClockTrackerTime)
    }
}