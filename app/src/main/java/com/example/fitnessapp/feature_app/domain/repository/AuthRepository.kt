package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.UserData

interface AuthRepository {

    suspend fun signIn(mail: String, pass: String)
    suspend fun signInWithGoogle() : Boolean

    suspend fun signUp(mail: String, pass: String, userData: UserData)
    suspend fun signUpWithGoogle() : Boolean
    suspend fun createProfile(
        gender: String,
        birthdayData: String,
        weight: String,
        height: String
    )
    suspend fun selectPurpose(purpose: String)
}