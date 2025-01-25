package com.example.fitnessapp.feature_app.presentation.SignIn

import io.github.jan.supabase.compose.auth.composable.NativeSignInState

sealed class SignInEvent {

    data class EnterEmail(val value: String) : SignInEvent()
    data class EnterPassword(val value: String) : SignInEvent()

    data object ShowHidePassword : SignInEvent()
    data object SignInClick : SignInEvent()

    data object ForgotPassword : SignInEvent()

    data class SignInWithGoogle(val nativeSignInState: NativeSignInState) : SignInEvent()

    data object ResetException : SignInEvent()
    data class SetException(val value: String) : SignInEvent()
}