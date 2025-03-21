package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class GetSleepDataByDateUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(year: Int, month: Int, day: Int) : List<SleepTrackerModel>{
        return sleepRepository.getSleepDataByDate(year, month, day)
    }
}