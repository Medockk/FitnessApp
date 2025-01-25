package com.example.fitnessapp.feature_app.domain.usecase.Auth

import com.example.fitnessapp.feature_app.domain.repository.AuthRepository

class SelectPurposeUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(purpose: String){
        authRepository.selectPurpose(purpose)
    }
}