package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.DietaryRecommendation
import kotlinx.serialization.Serializable

@Serializable
data class DietaryRecommendationImpl(
    override val id: Int,
    override val image: String,
    override val title: String,
    override val description: String,
    override val calories: String,
    override val fat: String,
    override val protein: String,
    override val carbo: String,
    override val author: String,
    override val details: String,
    override val category: String
): DietaryRecommendation