package com.example.fitnessapp.feature_app.domain.usecase.Statistic

import com.example.fitnessapp.feature_app.domain.model.BodyMassIndexData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class GetUserBodyMassIndexUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : BodyMassIndexData{
        return userDataRepository.getUserBodyMassIndex()
    }

}