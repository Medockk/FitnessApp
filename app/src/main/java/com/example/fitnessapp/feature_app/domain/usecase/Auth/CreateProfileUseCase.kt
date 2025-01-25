package com.example.fitnessapp.feature_app.domain.usecase.Auth

import com.example.fitnessapp.feature_app.domain.repository.AuthRepository

class CreateProfileUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        gender: String,
        birthdayData: String,
        weight: String,
        height: String
    ){
        authRepository.createProfile(gender, birthdayData, weight, height)
    }
}