package com.example.fitnessapp.feature_app.presentation.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.UserData
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserStatisticsUseCase
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getUserStatisticsUseCase: GetUserStatisticsUseCase,
    private val getHeartRateUseCase: GetHeartRateUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

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

        getUserDataUseCase().collect {  userData ->
            when (userData){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(exception = userData.message.toString(), showIndicator = false)
                }
                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }
                is NetworkResult.Success<*> -> {
                    val bodyMassIndex = getBodyMassIndex(userData.data)
                    val bodyMassComment = getBodyMassComment(bodyMassIndex)
                    withContext(Dispatchers.Main) {
                        _state.value = state.value.copy(
                            userData = userData.data,
                            bodyMassIndex = bodyMassIndex,
                            bodyMassComments = bodyMassComment,
                            showIndicator = false
                        )
                    }
                }
            }
        }

        getHeartRateUseCase().collect {
            when (it){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(
                        exception = it.message ?: "unknown error",
                        showIndicator = false
                    )
                }
                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }
                is NetworkResult.Success<*> -> {
                    _state.value = state.value.copy(showIndicator = false)
                    it.data?.heartRateList?.forEach { char ->
                        if (char.toString() != " "){
                            withContext(Dispatchers.Main){
                                _state.value = state.value.copy(
                                    barChartList = state.value.barChartList.plus(char.toString().toFloat())
                                )
                            }
                        }
                    }
                }
            }
        }

        val userStatistics = getUserStatisticsUseCase()
        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                userStatistics = userStatistics
            )
        }
    }

    private fun getBodyMassComment(bodyMassIndex: Float): String {
        return if (bodyMassIndex > 40){
            "Ожирение 3 степени"
        }else if (bodyMassIndex > 20 && bodyMassIndex < 30){
            "Предожирение"
        }else if (bodyMassIndex < 16){
            "Дефицит веса"
        }
        else{
            ""
        }
    }

    private fun getBodyMassIndex(userData: UserData?) : Float{
        return if (userData != null){
            userData.weight.toFloat() / ((userData.height.toInt() / 100) * (userData.height.toInt() / 100))
        }else{
            0f
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}