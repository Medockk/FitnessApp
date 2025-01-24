package com.example.fitnessapp.feature_app.domain.usecase.Auth

import com.example.fitnessapp.feature_app.domain.repository.AuthRepository

class SignInUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(mail: String, pass: String){
        authRepository.signIn(mail, pass)
    }
}