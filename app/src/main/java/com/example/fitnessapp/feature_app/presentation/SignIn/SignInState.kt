package com.example.fitnessapp.feature_app.presentation.SignIn

data class SignInState(
    val email: String = "",
    val password: String = "",
    val showHidePasswordState: Boolean = false,

    val isComplete: Boolean = false,

    val exception: String = "",
)
