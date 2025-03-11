package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.Purpose
import kotlinx.serialization.Serializable

@Serializable
data class PurposeImpl(
    override val id: Int,
    override val userID: String,
    override val purpose: String
) : Purpose
