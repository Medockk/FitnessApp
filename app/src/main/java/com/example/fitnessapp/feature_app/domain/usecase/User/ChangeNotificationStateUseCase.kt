package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class ChangeNotificationStateUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke(value: Boolean){
        userDataRepository.changeNotificationState(value)
    }
}