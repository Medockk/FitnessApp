package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class GetSleepDataUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke() : List<SleepTrackerModel>{
        return sleepRepository.getSleepData()
    }
}