package com.example.fitnessapp.feature_app.domain.usecase.Gallery

import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.repository.GalleryRepository

class GetGalleryFromMonthToMonthUseCase(
    private val galleryRepository: GalleryRepository
) {

    suspend operator fun invoke(firstMonth: Int, secondMonth: Int) : List<GalleryData>{
        return galleryRepository.getGalleryFromMonthToMonth(firstMonth, secondMonth)
    }
}