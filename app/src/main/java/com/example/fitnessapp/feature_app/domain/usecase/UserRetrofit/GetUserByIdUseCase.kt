package com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRetrofitRepository
import kotlinx.coroutines.flow.Flow

class GetUserByIdUseCase(
    private val userDataRetrofitRepository: UserDataRetrofitRepository
) {

    suspend operator fun invoke(id: Int) : Flow<NetworkResult<UserRetrofitData>>{
        return userDataRetrofitRepository.getUserById(id)
    }
}