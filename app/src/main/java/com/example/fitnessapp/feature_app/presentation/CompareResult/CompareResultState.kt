package com.example.fitnessapp.feature_app.presentation.CompareResult

import com.example.fitnessapp.feature_app.domain.model.GalleryData

data class CompareResultState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val middleProgress: String = "Хорошо",
    val floatMiddleProgress: Float = 0.7f,

    val firstMonth: String = "",
    val secondMonth: String = "",

    val gallery: List<GalleryData> = emptyList(),

    val statisticList: List<Float> = listOf(5f, 3f, 4f, 1f, 1f, 2f, 5f),
)
