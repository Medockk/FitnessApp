package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.data.model.UserDataImpl
import com.example.fitnessapp.feature_app.domain.model.HeartRate
import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import com.example.fitnessapp.feature_app.domain.model.NotificationData
import com.example.fitnessapp.feature_app.domain.model.Purpose
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.UserStatistics

interface UserDataRepository {

    suspend fun getUserData() : UserDataImpl
    suspend fun updateUserData(userData: UserData)

    suspend fun getUserStatistics() : List<UserStatistics>
    suspend fun getHeartRate() : HeartRate

    suspend fun getNotifications() : List<NotificationData>

    suspend fun getPurpose() : Purpose

    suspend fun getLastActivity() : List<LastActivityData>

    suspend fun getUserImage() : String
    suspend fun setUserImage(byteArray: ByteArray)
    suspend fun changeNotificationState(value: Boolean)
}