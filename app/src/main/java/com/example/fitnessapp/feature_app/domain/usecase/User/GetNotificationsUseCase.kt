package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.NotificationData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class GetNotificationsUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : List<NotificationData>{
        return userDataRepository.getNotifications()
    }
}