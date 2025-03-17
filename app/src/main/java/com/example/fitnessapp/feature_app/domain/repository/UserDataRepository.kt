package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.HeartRate
import com.example.fitnessapp.feature_app.domain.model.LastActivityData
import com.example.fitnessapp.feature_app.domain.model.NotificationData
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.UserStatistics
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    suspend fun getUserData(): Flow<NetworkResult<UserData>>

    suspend fun getUserStatistics(): List<UserStatistics>
    suspend fun getHeartRate(): Flow<NetworkResult<HeartRate>>

    suspend fun getNotifications(): Flow<NetworkResult<List<NotificationData>>>

    suspend fun getPurpose(): Flow<NetworkResult<String>>

    suspend fun getLastActivity(): Flow<NetworkResult<List<LastActivityData>>>

    suspend fun setUserImage(byteArray: ByteArray)
    suspend fun changeNotificationState(value: Boolean)
}