package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryData(
    val id: Int,
    val title: String,
    val image: String
)
