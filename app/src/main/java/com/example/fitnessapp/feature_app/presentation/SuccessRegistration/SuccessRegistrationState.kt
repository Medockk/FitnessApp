package com.example.fitnessapp.feature_app.presentation.SuccessRegistration

import com.example.fitnessapp.feature_app.domain.model.UserDataInter

data class SuccessRegistrationState(
    val userData: UserDataInter? = null,

    val exception: String = "",
)
