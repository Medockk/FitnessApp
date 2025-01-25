package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.UserData

interface UserDataRepository {

    suspend fun getUserData() : UserData

    suspend fun updateUserData(userData: UserData)
}