package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class GetUserImageUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : String{
        return userDataRepository.getUserImage()
    }
}