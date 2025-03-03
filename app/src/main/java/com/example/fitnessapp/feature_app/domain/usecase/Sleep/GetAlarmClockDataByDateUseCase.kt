package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class GetAlarmClockDataByDateUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(date: Int) : List<AlarmClockTracker>{
        return sleepRepository.getAlarmClockDataByDate(date)
    }
}