package com.example.fitnessapp.feature_app.presentation.SuccessRegistration

import com.example.fitnessapp.feature_app.domain.model.UserData

data class SuccessRegistrationState(
    val userData: UserData = UserData(0,"","","","","","",""),

    val exception: String = "",
)
