package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class UpdateUserDataUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke(userData: UserData){
        userDataRepository.updateUserData(userData)
    }
}