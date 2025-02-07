package com.example.fitnessapp.feature_app.presentation.ProgressPhoto

import com.example.fitnessapp.feature_app.domain.model.GalleryData

data class ProgressPhotoState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val gallery: List<GalleryData> = emptyList(),
    val nextPhotoDate: String = "",
)