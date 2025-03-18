package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class GetAlarmClockDataByDateUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(year: Int, month: Int, day: Int) : List<AlarmClockTrackerModel>{
        return sleepRepository.getAlarmClockDataByDate(year, month, day)
    }
}