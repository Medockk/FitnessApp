package com.example.fitnessapp.feature_app.presentation.Home

import com.example.fitnessapp.feature_app.domain.model.UserDataInter
import com.example.fitnessapp.feature_app.domain.model.UserStatistics

data class HomeState(
    val userData: UserDataInter? = null,

    val bodyMassIndex: Float = 0.0f,
    val bodyMassComments: String = "",

    val heartRate: Int = 78,
    val barChartList: List<Float> = emptyList(),

    val userStatistics: List<UserStatistics> = emptyList(),

    val exception: String = "",
    val showIndicator: Boolean = false,
)
