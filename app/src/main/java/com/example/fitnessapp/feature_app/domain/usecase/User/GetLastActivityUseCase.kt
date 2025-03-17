package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class GetLastActivityUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : Flow<NetworkResult<List<LastActivityData>>> {
        return userDataRepository.getLastActivity()
    }
}