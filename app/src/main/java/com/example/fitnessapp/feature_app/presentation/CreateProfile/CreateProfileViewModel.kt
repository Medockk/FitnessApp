package com.example.fitnessapp.feature_app.presentation.CreateProfile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateProfileViewModel : ViewModel() {

    private val _state = mutableStateOf(CreateProfileState())
    val state: State<CreateProfileState> = _state

    fun onEvent(event: CreateProfileEvent){
        when (event){
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
        }
    }
}