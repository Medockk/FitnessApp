package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationData(
    val id: Int,
    val data: String,
    val userID: String,
    val title: String,
    val image: String
)
