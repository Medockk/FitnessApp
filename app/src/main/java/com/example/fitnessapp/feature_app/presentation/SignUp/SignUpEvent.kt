package com.example.fitnessapp.feature_app.presentation.SignUp

sealed class SignUpEvent {

    data class EnterFIO(val value: String) : SignUpEvent()
    data class EnterPhone(val value: String) : SignUpEvent()
    data class EnterEmail(val value: String) : SignUpEvent()
    data class EnterPassword(val value: String) : SignUpEvent()

    data object ChangeShowHidePasswordState : SignUpEvent()
    data class ChangeCheckBoxState(val value: Boolean) : SignUpEvent()

    data object SignUpWithGoogle : SignUpEvent()

    data class SetException(val value: String) : SignUpEvent()
    data object ResetException : SignUpEvent()
    data object SignUp : SignUpEvent()
}