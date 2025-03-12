package com.example.fitnessapp.feature_app.domain.usecase.Compare

import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository
import kotlinx.coroutines.flow.Flow

class GetAllGalleryUseCase(
    private val compareRepository: CompareRepository
) {

    suspend operator fun invoke(): Flow<NetworkResult<List<GalleryData>>> {
        return compareRepository.getAllGallery()
    }
}