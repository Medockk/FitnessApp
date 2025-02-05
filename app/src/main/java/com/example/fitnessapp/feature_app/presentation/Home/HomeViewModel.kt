package com.example.fitnessapp.feature_app.presentation.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Statistic.GetUserStatisticsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
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

        val userData = getUserDataUseCase()
        val bodyMassIndex =
            userData.weight.toFloat() / ((userData.height.toInt() / 100) * (userData.height.toInt() / 100))
        val bodyMassComment = if (bodyMassIndex > 40){
            "Ожирение 3 степени"
        }else if (bodyMassIndex > 20 && bodyMassIndex < 30){
            "Предожирение"
        }else if (bodyMassIndex < 16){
            "Дефицит веса"
        }
        else{
            ""
        }


        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                userData = userData,
                bodyMassIndex = bodyMassIndex,
                bodyMassComments = bodyMassComment
            )
        }

        val heartRate = getHeartRateUseCase()
        heartRate.heartRateList.forEach { char ->
            if (char.toString() != " "){
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        barChartList = state.value.barChartList.plus(char.toString().toFloat())
                    )
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