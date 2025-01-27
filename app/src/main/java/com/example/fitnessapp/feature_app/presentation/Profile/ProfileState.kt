package com.example.fitnessapp.feature_app.presentation.Profile

import com.example.fitnessapp.feature_app.domain.model.Purpose
import com.example.fitnessapp.feature_app.domain.model.UserData

data class ProfileState(

    val userData: UserData = UserData(0,"","","","","","",""),
    val purpose: Purpose = Purpose(0,"",""),

    val isNotificationTurnOn: Boolean = false,

    val exception: String = "",
)
