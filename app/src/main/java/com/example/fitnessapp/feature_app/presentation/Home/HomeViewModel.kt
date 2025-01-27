package com.example.fitnessapp.feature_app.presentation.Home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Statistic.GetUserBodyMassIndexUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Statistic.GetUserStatisticsUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetUserDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getUserStatisticsUseCase: GetUserStatisticsUseCase,
    private val getUserBodyMassIndexUseCase: GetUserBodyMassIndexUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserData()
        }
    }

    private suspend fun getUserData() {

        val userData = getUserDataUseCase()
        val bodyMassIndex = getUserBodyMassIndexUseCase()
        val userStatistics = getUserStatisticsUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                userData = userData,
                bodyMassIndex = bodyMassIndex.value,
                userStatistics = userStatistics
            )
        }
    }

    fun onEvent(event: HomeEvent){
        when (event){
            HomeEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}