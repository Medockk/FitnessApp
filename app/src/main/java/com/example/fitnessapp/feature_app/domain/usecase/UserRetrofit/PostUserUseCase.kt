package com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRetrofitRepository
import kotlinx.coroutines.flow.Flow

class PostUserUseCase(
    private val userDataRetrofitRepository: UserDataRetrofitRepository
) {

    suspend operator fun invoke(userId: Int, name: String, status: String) : Flow<NetworkResult<UserRetrofitData>>{
        return userDataRetrofitRepository.postUser(userId, name, status)
    }
}