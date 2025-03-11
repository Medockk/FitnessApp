package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.HeartRate
import kotlinx.serialization.Serializable

@Serializable
data class HeartRateImpl(
    override val id: Int,
    override val userID: String,
    override val heartRateList: String
) : HeartRate
