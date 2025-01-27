package com.example.fitnessapp.feature_app.domain.usecase.User

import com.example.fitnessapp.feature_app.domain.model.Purpose
import com.example.fitnessapp.feature_app.domain.repository.UserDataRepository

class GetPurposeUseCase(
    private val userDataRepository: UserDataRepository
) {

    suspend operator fun invoke() : Purpose{
        return userDataRepository.getPurpose()
    }
}