package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData
import kotlinx.coroutines.flow.Flow

interface UserDataRetrofitRepository {

    suspend fun getUserById(id: Int) : Flow<NetworkResult<UserRetrofitData>>
}