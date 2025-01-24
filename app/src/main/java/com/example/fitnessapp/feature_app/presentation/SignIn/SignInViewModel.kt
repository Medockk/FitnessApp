package com.example.fitnessapp.feature_app.presentation.SignIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    fun onEvent(event: SignInEvent){
        when (event){
            is SignInEvent.EnterEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }
            is SignInEvent.EnterPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }
            SignInEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
            SignInEvent.ShowHidePassword -> {
                _state.value = state.value.copy(
                    showHidePasswordState = !state.value.showHidePasswordState
                )
            }

            SignInEvent.SignInClick -> {

            }
            SignInEvent.SignInWithGoogle -> {

            }

            SignInEvent.ForgotPassword -> {

            }
        }
    }
}