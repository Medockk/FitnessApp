package com.example.fitnessapp.feature_app.presentation.Profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.usecase.User.ChangeNotificationStateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetPurposeUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserImageUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.SetUserImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getPurposeUseCase: GetPurposeUseCase,
    private val getUserImageUseCase: GetUserImageUseCase,
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
                if (state.value.image.isEmpty()) {
                    _state.value = state.value.copy(
                        image = if (_state.value.userData?.gender == UserData.FEMALE) {
                            "https://avatars.mds.yandex.net/i?id=897113eed31435614b7bd5aa7b85fbbdb49c4efb-13071285-images-thumbs&n=13"
                        } else {
                            "https://avatars.mds.yandex.net/i?id=d220f7ba1825ae3131662553c80bd138bcb0d782-5492023-images-thumbs&n=13"
                        }
                    )
                } else {
                    _state.value = state.value.copy(
                        exception = e.message.toString(),
                        showIndicator = false
                    )
                }

            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getUserData() {

        getUserDataUseCase().collect { userData ->
            when (userData){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(exception = userData.message.toString(), showIndicator = false)
                }
                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }
                is NetworkResult.Success<*> -> {
                    withContext(Dispatchers.Main) {
                        _state.value = state.value.copy(
                            userData = userData.data,
                            showIndicator = false
                        )
                    }
                }
            }
        }

        val purpose = getPurposeUseCase()
        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                purpose = purpose
            )
        }

        val userImage = getUserImageUseCase()
        withContext(Dispatchers.IO) {
            _state.value = state.value.copy(
                image = userImage
            )
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