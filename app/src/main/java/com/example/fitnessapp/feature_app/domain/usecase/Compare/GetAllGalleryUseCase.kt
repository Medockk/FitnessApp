package com.example.fitnessapp.feature_app.domain.usecase.Compare

import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository

class GetAllGalleryUseCase(
    private val compareRepository: CompareRepository
) {

    suspend operator fun invoke(): List<GalleryData> {
        return compareRepository.getAllGallery()
    }
}