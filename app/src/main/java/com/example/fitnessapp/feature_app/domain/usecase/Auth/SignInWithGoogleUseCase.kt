package com.example.fitnessapp.feature_app.domain.usecase.Auth

import com.example.fitnessapp.feature_app.domain.repository.AuthRepository

class SignInWithGoogleUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(){
        authRepository.signInWithGoogle()
    }
}