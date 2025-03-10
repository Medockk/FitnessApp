package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.HeartRate
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class GetHeartRateUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : HeartRate {
        return userDataRepository.getHeartRate()
    }
}