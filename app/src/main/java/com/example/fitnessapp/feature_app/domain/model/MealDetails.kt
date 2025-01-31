package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MealDetails(
    val id: Int,
    val stepSize: String,
    val stepDescription: String,
    val ingredientsAndTheyCount: String
)
