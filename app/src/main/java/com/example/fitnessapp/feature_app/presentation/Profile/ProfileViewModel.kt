package com.example.fitnessapp.feature_app.presentation.Profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.usecase.User.ChangeNotificationStateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.SetUserImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getPurposeUseCase: GetPurposeUseCase,
    private val setUserImageUseCase: SetUserImageUseCase,
    private val changeNotificationStateUseCase: ChangeNotificationStateUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)

            try {
                getUserData()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getUserData() {

        getUserDataUseCase().collect { userData ->
            when (userData) {
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(
                        exception = userData.message.toString(),
                        showIndicator = false
                    )
                }

                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }

                is NetworkResult.Success<*> -> {
                    withContext(Dispatchers.Main) {
                        _state.value = state.value.copy(
                            userData = userData.data,
                            showIndicator = false,
                            image = userData.data?.image ?: ""
                        )
                    }
                }
            }
        }

        getPurposeUseCase().collect {
            when (it) {
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(
                        exception = it.message ?: "Unknown error",
                        showIndicator = false
                    )
                }

                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }

                is NetworkResult.Success<*> -> {
                    withContext(Dispatchers.Main) {
                        _state.value = state.value.copy(
                            showIndicator = false,
                            purpose = it.data ?: ""
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ChangeNotificationState -> {
                _state.value = state.value.copy(
                    isNotificationTurnOn = event.value
                )
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        changeNotificationStateUseCase(event.value)
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                }
            }

            ProfileEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            is ProfileEvent.ChangeImage -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(showIndicator = true)
                    try {
                        setUserImageUseCase(event.byteArray!!)
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString(),
                            showIndicator = false
                        )
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }

            is ProfileEvent.ChangeImageView -> {
                try {
                    _state.value = state.value.copy(
                        image = event.uri.toString()
                    )
                } catch (e: Exception) {
                    _state.value = state.value.copy(
                        exception = e.message.toString()
                    )
                }
            }
        }
    }
}