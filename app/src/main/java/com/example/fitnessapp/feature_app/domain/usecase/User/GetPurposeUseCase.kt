package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class GetPurposeUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : Flow<NetworkResult<String>>{
        return userDataRepository.getPurpose()
    }
}