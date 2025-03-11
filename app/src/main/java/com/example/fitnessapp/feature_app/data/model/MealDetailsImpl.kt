package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.MealDetails
import kotlinx.serialization.Serializable

@Serializable
data class MealDetailsImpl(
    override val id: Int,
    override val stepSize: String,
    override val stepDescription: String,
    override val ingredientsAndTheyCount: String
) : MealDetails
