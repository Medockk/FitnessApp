package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.model.StatisticData

interface CompareRepository {

    suspend fun getAllGallery() : List<GalleryData>

    suspend fun getGalleryFromMonthToMonth(firstMonth: String, secondMonth: String) : List<GalleryData>
    suspend fun getStatisticFromMonthToMonth(firstMonth: String, secondMonth: String) : List<StatisticData>

    suspend fun uploadPhoto(photo: ByteArray, category: String)
}