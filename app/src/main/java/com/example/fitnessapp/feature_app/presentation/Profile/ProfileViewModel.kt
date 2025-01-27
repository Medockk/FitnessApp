package com.example.fitnessapp.feature_app.presentation.Profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.User.GetPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getPurposeUseCase: GetPurposeUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getUserData()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString()
                )
            }
        }
    }

    private suspend fun getUserData() {

        val userData = getUserDataUseCase()
        val purpose = getPurposeUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                userData = userData,
                purpose = purpose
            )
        }
    }

    fun onEvent(event: ProfileEvent){
        when (event){
            is ProfileEvent.ChangeNotificationState -> {
                _state.value = state.value.copy(
                    isNotificationTurnOn = event.value
                )
            }
            ProfileEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}