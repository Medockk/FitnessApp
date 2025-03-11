package com.example.fitnessapp.feature_app.domain.repository

interface AuthRepository {

    suspend fun signIn(mail: String, pass: String)
    suspend fun signInWithGoogle(): Boolean

    suspend fun signUp(mail: String, pass: String, fio: String, phone: String)
    suspend fun signUpWithGoogle(): Boolean
    suspend fun createProfile(
        gender: String,
        birthdayData: String,
        weight: String,
        height: String
    )

    suspend fun selectPurpose(purpose: String)
}