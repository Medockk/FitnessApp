package com.example.fitnessapp.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class StatisticData(
    val date: String,
    val userID: String,
    val type: String,
    val value: String,
    val id: Int = 0,
){
    companion object{
        const val loseWeight = "Худеть"
        const val increasedGrowth = "Увеличение роста"
        const val increaseInMuscleMass = "Увеличение мышечной массы"
        const val press = "Пресс"
    }
}