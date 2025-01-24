package com.example.fitnessapp.feature_app.presentation.SignUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EnterEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }

            is SignUpEvent.EnterFIO -> {
                _state.value = state.value.copy(
                    fio = event.value
                )
            }

            is SignUpEvent.EnterPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }

            is SignUpEvent.EnterPhone -> {
                _state.value = state.value.copy(
                    phone = event.value
                )
            }

            SignUpEvent.SignUp -> {
                if (
                    _state.value.fio.isNotBlank() &&
                    _state.value.phone.isNotBlank() &&
                    _state.value.email.isNotBlank() &&
                    _state.value.password.isNotBlank() &&
                    _state.value.checkBoxState
                ){
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            signUpUseCase(
                                mail = _state.value.email,
                                pass = _state.value.password,
                                userData = UserData(
                                    0,"",
                                    fio = _state.value.fio,
                                    phone = _state.value.phone
                                )
                            )
                            _state.value = state.value.copy(
                                isComplete = true
                            )
                        } catch (e: Exception) {
                            _state.value = state.value.copy(
                                exception = e.message.toString()
                            )
                        }
                    }
                }else if (_state.value.fio.isBlank()){
                    _state.value = state.value.copy(
                        exception = "Поле ФИО не может быть пустым!"
                    )
                }else if (_state.value.phone.isBlank()){
                    _state.value = state.value.copy(
                        exception = "Поле Номер Телефона не может быть пустым!"
                    )
                }else if (_state.value.email.isBlank()){
                    _state.value = state.value.copy(
                        exception = "Поле Почта не может быть пустым!"
                    )
                }else if (_state.value.password.isBlank()){
                    _state.value = state.value.copy(
                        exception = "Поле Пароль не может быть пустым!"
                    )
                }else if (!_state.value.checkBoxState){
                    _state.value = state.value.copy(
                        exception = "Примите Политику Конфеденциальности!"
                    )
                }
            }

            SignUpEvent.ChangeShowHidePasswordState -> {
                _state.value = state.value.copy(
                    showHidePasswordState = !state.value.showHidePasswordState
                )
            }

            is SignUpEvent.ChangeCheckBoxState -> {
                _state.value = state.value.copy(
                    checkBoxState = event.value
                )
            }

            SignUpEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            is SignUpEvent.SetException -> {
                _state.value = state.value.copy(
                    exception = event.value
                )
            }
        }
    }
}