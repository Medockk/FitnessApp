package com.example.fitnessapp.feature_app.domain.usecase.Compare

import com.example.fitnessapp.feature_app.domain.model.GalleryData
import com.example.fitnessapp.feature_app.domain.repository.CompareRepository

class GetGalleryFromMonthToMonthUseCase(
    private val compareRepository: CompareRepository
) {

    suspend operator fun invoke(firstMonth: String, secondMonth: String): List<GalleryData> {
        return compareRepository.getGalleryFromMonthToMonth(firstMonth, secondMonth)
    }
}