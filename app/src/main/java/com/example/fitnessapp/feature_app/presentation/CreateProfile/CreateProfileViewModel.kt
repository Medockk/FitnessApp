package com.example.fitnessapp.feature_app.presentation.CreateProfile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.usecase.Auth.CreateProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateProfileViewModel(
    private val createProfileUseCase: CreateProfileUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CreateProfileState())
    val state: State<CreateProfileState> = _state

    fun onEvent(event: CreateProfileEvent) {
        when (event) {
            is CreateProfileEvent.EnterBirthdayData -> {
                _state.value = state.value.copy(
                    birthdayData = event.value
                )
            }

            is CreateProfileEvent.EnterGender -> {
                _state.value = state.value.copy(
                    gender = event.value
                )
            }

            is CreateProfileEvent.EnterHeight -> {
                _state.value = state.value.copy(
                    height = event.value
                )
            }

            is CreateProfileEvent.EnterWeight -> {
                _state.value = state.value.copy(
                    weight = event.value
                )
            }

            CreateProfileEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            CreateProfileEvent.CreateProfileClick -> {
                if (
                    _state.value.gender.isNotBlank() &&
                    (_state.value.gender == UserData.male || _state.value.gender == UserData.female) &&
                    _state.value.birthdayData.isNotBlank() &&
                    _state.value.weight.isNotBlank() &&
                    _state.value.height.isNotBlank()
                ) {
                    viewModelScope.launch(Dispatchers.IO) {
                        _state.value = state.value.copy(showIndicator = true)
                        try {
                            createProfileUseCase(
                                gender = _state.value.gender,
                                birthdayData = _state.value.birthdayData,
                                weight = _state.value.weight,
                                height = _state.value.height
                            )
                            _state.value = state.value.copy(
                                showIndicator = false,
                                isComplete = true
                            )
                        } catch (e: Exception) {
                            _state.value = state.value.copy(
                                exception = e.message.toString(),
                                showIndicator = false
                            )
                        }
                    }
                } else if (_state.value.gender.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле Ваш Пол не может быть пустым!",
                        showIndicator = false
                    )
                } else if (_state.value.gender != UserData.male || _state.value.gender != UserData.female) {
                    _state.value = state.value.copy(
                        exception = "Поле Ваш Пол не может принимать других значений:(",
                        showIndicator = false
                    )
                } else if (_state.value.birthdayData.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле Дата Рождения не может быть пустым!",
                        showIndicator = false
                    )
                } else if (_state.value.weight.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле Ваш Вес не может быть пустым!",
                        showIndicator = false
                    )
                } else if (_state.value.height.isBlank()) {
                    _state.value = state.value.copy(
                        exception = "Поле Ваш Рост не может быть пустым!",
                        showIndicator = false
                    )
                } else {
                    _state.value = state.value.copy(
                        exception = "Непредвиденная ошибка!",
                        showIndicator = false
                    )
                }
            }

            CreateProfileEvent.ChangeDropDownMenuState -> {
                _state.value = state.value.copy(
                    isDropDownMenuOpen = !state.value.isDropDownMenuOpen
                )
            }
        }
    }
}