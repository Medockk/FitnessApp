package com.example.fitnessapp.feature_app.presentation.RegisterPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Auth.SelectPurposeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterPageViewModel(
    private val selectPurposeUseCase: SelectPurposeUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RegisterPageState())
    val state: State<RegisterPageState> = _state

    fun onEvent(event: RegisterEvent){
        when (event){
            is RegisterEvent.SelectPurpose -> {
                viewModelScope.launch(Dispatchers.IO) {
                    selectPurposeUseCase(
                        _state.value.yourPurpose[event.value]
                            .title
                    )
                    _state.value = state.value.copy(
                        isComplete = true
                    )
                }
            }
        }
    }
}