package com.example.fitnessapp.feature_app.domain.usecase.Gallery

import com.example.fitnessapp.feature_app.domain.repository.GalleryRepository

class UploadPhotoUseCase(
    private val galleryRepository: GalleryRepository
) {

    suspend operator fun invoke(photo: ByteArray, category: String){
        galleryRepository.uploadPhoto(photo, category)
    }
}