package com.example.fitnessapp.feature_app.presentation.SuccessRegistration

import com.example.fitnessapp.feature_app.domain.model.UserData

data class SuccessRegistrationState(
    val userData: UserData? = null,
    val showIndicator: Boolean = true,

    val exception: String = "",
)
