package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class ChangeAlarmEnabledUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(alarmClockTrackerId: Int, alarmClockTrackerEnabled: Boolean, ) {
        sleepRepository.changeAlarmEnabled(alarmClockTrackerId, alarmClockTrackerEnabled,)
    }
}