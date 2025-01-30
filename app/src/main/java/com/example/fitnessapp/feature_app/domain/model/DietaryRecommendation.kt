package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DietaryRecommendation(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val calories: String,
    val fat: String,
    val protein: String,
    val carbo: String
)
