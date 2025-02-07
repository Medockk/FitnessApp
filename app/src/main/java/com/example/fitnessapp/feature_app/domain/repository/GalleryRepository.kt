package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.GalleryData

interface GalleryRepository {

    suspend fun getAllGallery() : List<GalleryData>

    suspend fun getGalleryFromMonthToMonth(firstMonth: Int, secondMonth: Int) : List<GalleryData>

    suspend fun uploadPhoto(photo: ByteArray, category: String)
}