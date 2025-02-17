package com.example.fitnessapp.feature_app.domain.usecase.Compare

import android.graphics.Bitmap
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository

class UploadPhotoUseCase(
    private val compareRepository: CompareRepository
) {

    suspend operator fun invoke(photo: Bitmap, category: String){
        compareRepository.uploadPhoto(photo, category)
    }
}