package com.example.fitnessapp.feature_app.domain.usecase.Auth

import com.example.fitnessapp.feature_app.domain.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(mail: String, pass: String, fio: String, phone: String){
        authRepository.signUp(mail, pass, fio, phone)
    }
}