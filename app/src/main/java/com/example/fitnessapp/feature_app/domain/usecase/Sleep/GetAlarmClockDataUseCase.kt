package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class GetAlarmClockDataUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke() : List<AlarmClockTracker>{
        return sleepRepository.getAlarmClockData()
    }
}