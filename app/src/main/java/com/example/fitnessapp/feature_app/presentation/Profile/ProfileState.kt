package com.example.fitnessapp.feature_app.presentation.Profile

import com.example.fitnessapp.feature_app.domain.model.Purpose
import com.example.fitnessapp.feature_app.domain.model.UserData

data class ProfileState(

    val userData: UserData? = null,
    val purpose: Purpose? = null,
    val image: String = "",

    val isNotificationTurnOn: Boolean = false,

    val exception: String = "",

    val showIndicator: Boolean = false,
    val isInit: Boolean = true,
)
