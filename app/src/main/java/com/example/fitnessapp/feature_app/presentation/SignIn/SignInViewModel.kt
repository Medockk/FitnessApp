package com.example.fitnessapp.feature_app.presentation.SignIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignInWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
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
                            _state.value = state.value.copy(
                                showIndicator = true
                            )
                            signInUseCase(
                                _state.value.email,
                                state.value.password
                            )
                            _state.value = state.value.copy(
                                showIndicator = false,
                                isRegistered = true
                            )
                        }else if (_state.value.email.isBlank()){
                            _state.value = state.value.copy(
                                exception = "Поле Почта не может быть пустым!",
                                showIndicator = false
                            )
                        }else if (_state.value.password.isBlank()){
                            _state.value = state.value.copy(
                                exception = "Поле Пароль не может быть пустым!",
                                showIndicator = false
                            )
                        }
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString(),
                            showIndicator = false
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
                    try {
                        val result = signInWithGoogleUseCase()

                        if (result){
                            _state.value = state.value.copy(
                                isRegistered = true
                            )
                        }else{
                            _state.value = state.value.copy(
                                exception = "Пользователь не зарегистрирован"
                            )
                        }
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }
        }
    }
}