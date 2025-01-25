package com.example.fitnessapp.feature_app.presentation.SignIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

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
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        if (
                            _state.value.email.isNotBlank() &&
                            _state.value.password.isNotBlank()
                        ){
                            signInUseCase(
                                _state.value.email,
                                state.value.password
                            )
                            _state.value = state.value.copy(
                                isComplete = true
                            )
                        }else if (_state.value.email.isBlank()){
                            _state.value = state.value.copy(
                                exception = "Поле Почта не может быть пустым!"
                            )
                        }else if (_state.value.password.isBlank()){
                            _state.value = state.value.copy(
                                exception = "Поле Пароль не может быть пустым!"
                            )
                        }
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }
            SignInEvent.ForgotPassword -> {

            }

            is SignInEvent.SetException -> {
                _state.value = state.value.copy(
                    exception = event.value
                )
            }

            is SignInEvent.SignInWithGoogle -> {
                viewModelScope.launch(Dispatchers.IO) {
                    event.nativeSignInState.startFlow()
                }
            }
        }
    }
}