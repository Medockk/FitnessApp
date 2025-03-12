package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class GetUserDataUseCase(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(): Flow<NetworkResult<UserData>> {
        return userDataRepository.getUserData()
    }
}