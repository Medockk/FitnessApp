package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.NotificationData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : Flow<NetworkResult<List<NotificationData>>>{
        return userDataRepository.getNotifications()
    }
}