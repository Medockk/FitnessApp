package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.BodyMassIndexData
import com.example.fitnessapp.feature_app.domain.model.NotificationData
import com.example.fitnessapp.feature_app.domain.model.Purpose
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.UserStatistics

interface UserDataRepository {

    suspend fun getUserData() : UserData
    suspend fun updateUserData(userData: UserData)

    suspend fun getUserStatistics() : List<UserStatistics>
    suspend fun getUserBodyMassIndex() : BodyMassIndexData

    suspend fun getNotifications() : List<NotificationData>

    suspend fun getPurpose() : Purpose
}