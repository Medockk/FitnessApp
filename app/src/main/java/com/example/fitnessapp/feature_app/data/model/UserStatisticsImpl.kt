package com.example.fitnessapp.feature_app.data.model

import com.example.fitnessapp.feature_app.domain.model.UserStatistics
import kotlinx.serialization.Serializable

@Serializable
data class UserStatisticsImpl(
    override val id: Int,
    override val userID: String,
    override val title: String,
    override val description: String
) : UserStatistics
