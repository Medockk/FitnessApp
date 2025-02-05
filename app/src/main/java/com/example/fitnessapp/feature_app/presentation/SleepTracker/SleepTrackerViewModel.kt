package com.example.fitnessapp.feature_app.presentation.SleepTracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime

class SleepTrackerViewModel(
    private val getSleepDataUseCase: GetSleepDataUseCase,
    private val changeEnabledUseCase: ChangeEnabledUseCase,
    private val getHeartRateUseCase: GetHeartRateUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SleepTrackerState())
    val state: State<SleepTrackerState> = _state

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

        val barList = getHeartRateUseCase()
        val sleep = getSleepDataUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                sleepData = sleep,
                lastSleep = if (sleep.isNotEmpty()) sleep.first().lastSleep else "Ничего нет :(",
                currentTime = LocalDateTime.parse(Clock.System.now().toString().dropLast(1))
            )
        }

        var count = 0
        barList.heartRateList.forEach {
            if (it.isDigit() && count<8){
                count++
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        barList = _state.value.barList.plus(it.toString().toFloat())
                    )
                }
            }
        }
    }

    fun onEvent(event: SleepTrackerEvent){
        when (event){
            is SleepTrackerEvent.ChangeSleepWorkout -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val index = _state.value.sleepData.indexOf(event.sleepTracker)
                        val newData = _state.value.sleepData[index].copy(enabled = !event.sleepTracker.enabled)
                        _state.value = state.value.copy(
                            sleepData = _state.value.sleepData - event.sleepTracker
                                + newData
                        )
                        changeEnabledUseCase(event.sleepTracker.copy(enabled = !event.sleepTracker.enabled))
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }
            SleepTrackerEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}