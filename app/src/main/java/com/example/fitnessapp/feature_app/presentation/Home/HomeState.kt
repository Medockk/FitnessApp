package com.example.fitnessapp.feature_app.presentation.Home

import com.example.fitnessapp.feature_app.domain.model.UserData

data class HomeState(
    val userData: UserData = UserData(0,"","","","","","",""),

    val imt: Float = 0.0f,
    val isHaveNotification: Boolean = false,

    val exception: String = "",
)
