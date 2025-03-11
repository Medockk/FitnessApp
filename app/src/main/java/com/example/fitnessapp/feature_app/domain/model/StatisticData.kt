package com.example.fitnessapp.feature_app.domain.model

interface StatisticData {
    val date: String
    val userID: String
    val type: String
    val value: String
    val id: Int?

    companion object {
        const val LOSE_WEIGHT = "Худеть"
        const val INCREASED_GROWTH = "Увеличение роста"
        const val INCREASE_IN_MUSCLE_MASS = "Увеличение мышечной массы"
        const val PRESS = "Пресс"
    }
}