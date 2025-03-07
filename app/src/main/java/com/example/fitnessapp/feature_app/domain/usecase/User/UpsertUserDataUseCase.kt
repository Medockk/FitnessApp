package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.data.model.UserDataImpl
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class UpsertUserDataUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke(userDataImpl: UserDataImpl){
        userDataRepository.upsertUserData(userDataImpl)
    }
}