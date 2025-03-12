package com.example.fitnessapp.feature_app.presentation.SuccessRegistration

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.GetUserByIdUseCase
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.PostUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SuccessRegistrationViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val postUserUseCase: PostUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SuccessRegistrationState())
    val state: State<SuccessRegistrationState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getUserRetrofitData()
                getUserData()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString()
                )
            }
        }
    }

    private suspend fun getUserRetrofitData() {

        Log.e("retrofit", "start")
        postUserUseCase(3, "userName", "userStatus").onEach {
            when (it){
                is NetworkResult.Error<*> -> {
                    Log.e("retrofitVM1", "error1")
                    _state.value = state.value.copy(exception = it.message.toString(), showIndicator = false)
                }
                is NetworkResult.Loading<*> -> {
                    Log.e("retrofitVM1", "loading1")
                    _state.value = state.value.copy(showIndicator = true)
                }
                is NetworkResult.Success<*> -> {
                    Log.e("retrofitVM1", it.data.toString())
                    getUserByIdUseCase(3).onEach { getResult ->
                        when (getResult){
                            is NetworkResult.Error<*> -> {
                                Log.e("retrofitVM2", "error")
                                _state.value = state.value.copy(exception = getResult.message ?: "unknown error", showIndicator = false)
                            }
                            is NetworkResult.Loading<*> -> {
                                Log.e("retrofitVM2", "loading")
                                _state.value = state.value.copy(showIndicator = true)
                            }
                            is NetworkResult.Success<*> -> {
                                Log.e("retrofitVM2", getResult.data.toString())
                                _state.value = state.value.copy(
                                    showIndicator = false
                                )
                            }
                        }
                    }.stateIn(viewModelScope)
                }
            }
        }.stateIn(viewModelScope)
    }

    private suspend fun getUserData() {

        val userData = getUserDataUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                userData = userData
            )
        }
    }

    fun onEvent(event: SuccessRegistrationEvent){
        when (event){
            SuccessRegistrationEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}