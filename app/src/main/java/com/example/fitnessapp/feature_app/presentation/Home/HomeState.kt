package com.example.fitnessapp.feature_app.presentation.Home

import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.UserStatistics

data class HomeState(
    val userData: UserData = UserData(0,"","","","","","",""),

    val bodyMassIndex: Float = 0.0f,
    val bodyMassComments: String = "",

    val heartRate: Int = 0,
    val barChartList: List<Int> = listOf(0,0,0,0,0,0,0),

    val userStatistics: List<UserStatistics> = emptyList(),

    val exception: String = "",
)
