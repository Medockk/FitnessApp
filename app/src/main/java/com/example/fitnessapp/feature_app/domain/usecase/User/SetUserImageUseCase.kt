package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class SetUserImageUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke(byteArray: ByteArray){
        userDataRepository.setUserImage(byteArray)
    }
}