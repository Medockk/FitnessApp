package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.data.model.UserDataImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class GetUserDataDaoUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke(userID: String) : Flow<UserDataImpl>{
        return userDataRepository.getUserDataDao(userID)
    }
}