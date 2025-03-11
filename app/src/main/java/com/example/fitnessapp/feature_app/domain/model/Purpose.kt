package com.example.fitnessapp.feature_app.domain.model

interface Purpose {
    val id: Int
    val userID: String
    val purpose: String
}

data class CurrentPurpose(
    val id: Int,
    val userID: String,
    val title: String,
    val description: String,
    val image: String,
)