package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class ChangeSleepEnabledUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(sleepTracker: SleepTracker){
        sleepRepository.changeSleepEnabled(sleepTracker)
    }
}