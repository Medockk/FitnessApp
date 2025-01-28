package com.example.fitnessapp.feature_app.domain.usecase.Auth

import com.example.fitnessapp.feature_app.domain.repository.AuthRepository

class SignUpWithGoogleUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() : Boolean{
        return authRepository.signUpWithGoogle()
    }
}