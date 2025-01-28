package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Purpose(
    val id: Int,
    val userID: String,
    val purpose: String,
){
    companion object{
        const val improveYourForm = "Улучшить форму"
        const val tone = "Тонус"
        const val loseWeight = "Похудеть"
    }
}

@Serializable
data class CurrentPurpose(
    val id: Int,
    val userID: String,
    val title: String,
    val description: String,
    val image: String
)
