package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData
import kotlinx.coroutines.flow.Flow

interface UserDataRetrofitRepository {

    suspend fun getUserById(id: Int) : Flow<NetworkResult<UserRetrofitData>>
    suspend fun postUser(userId: Int, name: String, status: String) : Flow<NetworkResult<UserRetrofitData>>
}