package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int = 0,
    val userID: String,
    val name: String,
    val surname: String,
    val patronymic: String,
    val phone: String
)
