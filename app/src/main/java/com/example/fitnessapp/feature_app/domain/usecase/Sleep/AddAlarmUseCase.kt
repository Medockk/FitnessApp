package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class AddAlarmUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(sleepTracker: SleepTracker, alarmClockTracker: AlarmClockTracker){
        sleepRepository.addAlarm(sleepTracker, alarmClockTracker)
    }
}