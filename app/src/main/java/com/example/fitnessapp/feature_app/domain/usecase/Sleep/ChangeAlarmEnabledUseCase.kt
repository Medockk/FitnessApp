package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class ChangeAlarmEnabledUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(alarmClockTracker: AlarmClockTracker){
        sleepRepository.changeAlarmEnabled(alarmClockTracker)
    }
}