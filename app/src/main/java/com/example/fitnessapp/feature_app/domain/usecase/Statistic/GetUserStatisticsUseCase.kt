package com.example.fitnessapp.feature_app.domain.usecase.Statistic

import com.example.fitnessapp.feature_app.domain.model.UserStatistics
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class GetUserStatisticsUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : List<UserStatistics>{
        return userDataRepository.getUserStatistics()
    }
}