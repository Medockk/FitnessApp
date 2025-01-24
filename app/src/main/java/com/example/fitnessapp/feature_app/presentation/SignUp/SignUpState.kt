package com.example.fitnessapp.feature_app.presentation.SignUp

data class SignUpState(
    val fio: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",

    val showHidePasswordState: Boolean = false,
    val checkBoxState: Boolean = false,

    val successfulSignUpWithGoogle: Boolean = false,
    val isComplete: Boolean = false,

    val exception: String = "",
)
