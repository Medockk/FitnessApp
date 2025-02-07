package com.example.fitnessapp.feature_app.domain.usecase.Gallery

import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.repository.GalleryRepository

class GetAllGalleryUseCase(
    private val galleryRepository: GalleryRepository
) {

    suspend operator fun invoke() : List<GalleryData>{
        return galleryRepository.getAllGallery()
    }
}