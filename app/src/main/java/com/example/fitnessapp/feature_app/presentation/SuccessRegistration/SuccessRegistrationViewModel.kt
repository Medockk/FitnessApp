package com.example.fitnessapp.feature_app.presentation.SuccessRegistration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.GetUserByIdUseCase
import com.example.fitnessapp.feature_app.domain.usecase.UserRetrofit.PostUserUseCase
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuccessRegistrationViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val postUserUseCase: PostUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SuccessRegistrationState())
    val state: State<SuccessRegistrationState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                getUserRetrofitData()
                getUserData()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString()
                )
            }
        }
    }

//    private suspend fun getUserRetrofitData() {
//
//        postUserUseCase(3, "userName", "userStatus").collect {
//            when (it){
//                is NetworkResult.Error<*> -> {
//                    _state.value = state.value.copy(exception = it.message.toString(), showIndicator = false)
//                }
//                is NetworkResult.Loading<*> -> {
//                    _state.value = state.value.copy(showIndicator = true)
//                }
//                is NetworkResult.Success<*> -> {
//                    getUserByIdUseCase(3).collect { getResult ->
//                        when (getResult){
//                            is NetworkResult.Error<*> -> {
//                                _state.value = state.value.copy(exception = getResult.message ?: "unknown error", showIndicator = false)
//                            }
//                            is NetworkResult.Loading<*> -> {
//                                _state.value = state.value.copy(showIndicator = true)
//                            }
//                            is NetworkResult.Success<*> -> {
//                                _state.value = state.value.copy(
//                                    showIndicator = false,
//                                )
//                                Log.e("retrofit", getResult.data.toString())
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

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
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            userData = userData.data,
                            showIndicator = false
                        )
                    }
                }
            }
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