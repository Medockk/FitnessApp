package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepScheduleViewModel(
    private val getSleepDataUseCase: GetSleepDataUseCase,
    private val changeEnabledUseCase: ChangeEnabledUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SleepScheduleState())
    val state: State<SleepScheduleState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getSleepData()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getSleepData() {

        val sleep = getSleepDataUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                sleepData = sleep,
                sleepTime = if (sleep.isNotEmpty()) sleep.first().lastSleep else "Ничего нет :("
            )
        }
    }

    fun onEvent(event: SleepScheduleEvent){
        when (event){
            SleepScheduleEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }

            is SleepScheduleEvent.ChangeEnabled -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(showIndicator = true)
                    val index = _state.value.sleepData.indexOf(event.sleepTracker)
                    val newData = _state.value.sleepData[index].copy(enabled = !event.sleepTracker.enabled)
                    _state.value = state.value.copy(
                        sleepData = _state.value.sleepData - event.sleepTracker
                            + newData
                    )
                    try {
                        changeEnabledUseCase(event.sleepTracker)
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString(),
                            showIndicator = false
                        )
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }
        }
    }
}