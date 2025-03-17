package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.model.StatisticData
import kotlinx.coroutines.flow.Flow

interface CompareRepository {

    suspend fun getAllGallery() : Flow<NetworkResult<List<GalleryData>>>

    suspend fun getGalleryFromMonthToMonth(firstMonth: String, secondMonth: String) : List<GalleryData>
    suspend fun getStatisticFromMonthToMonth(firstMonth: String, secondMonth: String) : Flow<NetworkResult<List<StatisticData>>>

    suspend fun uploadPhoto(photo: ByteArray, category: String)
}