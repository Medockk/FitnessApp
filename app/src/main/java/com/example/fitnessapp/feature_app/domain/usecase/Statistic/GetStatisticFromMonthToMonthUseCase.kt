package com.example.fitnessapp.feature_app.domain.usecase.Statistic

import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.StatisticData
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository
import kotlinx.coroutines.flow.Flow

class GetStatisticFromMonthToMonthUseCase(
    private val compareRepository: CompareRepository
) {

    suspend operator fun invoke(firstMonth: String, secondMonth: String) : Flow<NetworkResult<List<StatisticData>>> {
        return compareRepository.getStatisticFromMonthToMonth(firstMonth, secondMonth)
    }
}