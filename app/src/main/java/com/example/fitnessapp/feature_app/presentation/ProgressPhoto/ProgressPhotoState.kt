package com.example.fitnessapp.feature_app.presentation.ProgressPhoto

data class ProgressPhotoState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val gallery: List<Int> = emptyList(),
    val nextPhotoDate: String = "",
)