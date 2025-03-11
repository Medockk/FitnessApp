package com.example.fitnessapp.feature_app.domain.usecase.Sleep

import com.example.fitnessapp.feature_app.domain.repository.SleepRepository

class ChangeSleepEnabledUseCase(
    private val sleepRepository: SleepRepository
) {

    suspend operator fun invoke(sleepTrackerId: Int, sleepTrackerEnabled: Boolean){
        sleepRepository.changeSleepEnabled(sleepTrackerId, sleepTrackerEnabled)
    }
}