package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LastActivityData(
    val id: Int,
    val userID: String,
    val title: String,
    val date: String,
    val image: String
)
