package com.example.fitnessapp.feature_app.presentation.SignUp

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SignUpWithGoogleUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Dao.UpsertUserDataDaoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val signUpWithGoogleUseCase: SignUpWithGoogleUseCase,
    private val upsertUserDataDaoUseCase: UpsertUserDataDaoUseCase
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

            is SignUpEvent.ChangeCheckBoxState -> {
                _state.value = state.value.copy(
                    checkBoxState = event.value
                )
            }

            SignUpEvent.ChangeShowHidePasswordState -> {
                _state.value = state.value.copy(
                    showHidePasswordState = !state.value.showHidePasswordState
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

            SignUpEvent.SignUp -> {
                if (!Patterns.EMAIL_ADDRESS.matcher(_state.value.email).matches()) {
                    _state.value = state.value.copy(exception = "Неверное имя почты")
                } else if (
                    _state.value.fio.isNotBlank() &&
                    _state.value.phone.isNotBlank() &&
                    _state.value.email.isNotBlank() &&
                    _state.value.password.isNotBlank() &&
                    _state.value.password.length >= 6 &&
                    _state.value.checkBoxState
                ) {
                    viewModelScope.launch(Dispatchers.IO) {
                        _state.value = state.value.copy(
                            showIndicator = true
                        )
//                        try {
//                            upsertUserDataDaoUseCase(
//                                UserDataEntity(
//                                    fio = _state.value.fio,
//                                    phone = _state.value.phone,
//                                    email = _state.value.email
//                                )
//                            )
//                        } catch (e: Exception) {
//                            _state.value = state.value.copy(exception = e.message.toString())
//                        }
                        try {
                            signUpUseCase(
                                mail = _state.value.email,
                                pass = _state.value.password,
                                userData = UserData(
                                    0, "",
                                    fio = _state.value.fio,
                                    phone = _state.value.phone
                                )
                            )
                            _state.value = state.value.copy(
                                showIndicator = false,
                                isFirstRegistration = true
                            )
                        } catch (e: Exception) {
                            _state.value = state.value.copy(
                                exception = e.message.toString(),
                                showIndicator = false
                            )
                        }
                    }

                } else if (_state.value.fio.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле ФИО не может быть пустым!",
                        showIndicator = false
                    )
                } else if (_state.value.phone.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле Номер Телефона не может быть пустым!",
                        showIndicator = false
                    )
                } else if (_state.value.email.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле Почта не может быть пустым!",
                        showIndicator = false
                    )
                } else if (_state.value.password.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле Пароль не может быть пустым!",
                        showIndicator = false
                    )
                } else if (!_state.value.checkBoxState) {
                    _state.value = state.value.copy(
                        exception = "Примите Политику Конфеденциальности!",
                        showIndicator = false
                    )
                } else if (_state.value.password.length < 6) {
                    _state.value = state.value.copy(
                        exception = "Пароль не может быть моньше 6 символов!",
                        showIndicator = false
                    )
                }
            }

            is SignUpEvent.SignUpWithGoogle -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val signUpState = signUpWithGoogleUseCase()

                        if (signUpState) {
                            _state.value = state.value.copy(
                                exception = "Ошибка!"
                            )
                        } else {
                            _state.value = state.value.copy(
                                isFirstRegistration = true
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