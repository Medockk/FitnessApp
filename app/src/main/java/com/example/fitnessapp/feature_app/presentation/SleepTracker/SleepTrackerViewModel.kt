package com.example.fitnessapp.feature_app.presentation.SleepTracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeAlarmEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeSleepEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetAlarmClockDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SleepTrackerViewModel @Inject constructor(
    private val getSleepDataUseCase: GetSleepDataUseCase,
    private val changeSleepEnabledUseCase: ChangeSleepEnabledUseCase,
    private val getHeartRateUseCase: GetHeartRateUseCase,
    private val getAlarmClockDataUseCase: GetAlarmClockDataUseCase,
    private val changeAlarmEnabledUseCase: ChangeAlarmEnabledUseCase
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

        getHeartRateUseCase().collect {
            when(it){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(
                        exception = it.message ?: "Unknown error",
                        showIndicator = false
                    )
                }
                is NetworkResult.Loading<*> -> {_state.value = state.value.copy(showIndicator = true)}
                is NetworkResult.Success<*> -> {
                    _state.value = state.value.copy(showIndicator = false)
                    var count = 0
                    it.data?.heartRateList?.forEach { char ->
                        if (char.isDigit() && count<8){
                            count++
                            withContext(Dispatchers.Main){
                                _state.value = state.value.copy(
                                    barList = _state.value.barList.plus(char.toString().toFloat())
                                )
                            }
                        }
                    }
                }
            }
        }
        val sleep = getSleepDataUseCase()
        val alarmClock = getAlarmClockDataUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                sleepData = if (sleep.isNotEmpty()) sleep.first() else _state.value.sleepData,
                alarmClockTracker = if (alarmClock.isNotEmpty()) alarmClock.first() else _state.value.alarmClockTracker,
                lastSleep = if (sleep.isNotEmpty()) sleep.first().lastSleep else "Ничего нет :(",
                currentTime = LocalDateTime.parse(Clock.System.now().toString().dropLast(1))
            )
        }
    }

    fun onEvent(event: SleepTrackerEvent){
        when (event){
            is SleepTrackerEvent.ChangeSleepWorkout -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val newSleepData = event.sleepTracker.copy(enabled = !event.sleepTracker.enabled)
                        _state.value = state.value.copy(
                            sleepData = newSleepData
                        )
                        changeSleepEnabledUseCase(event.sleepTracker.id, newSleepData.enabled)
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

            is SleepTrackerEvent.ChangeAlarmState -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val newAlarmData = event.alarmClockTracker.copy(enabled = !event.alarmClockTracker.enabled)
                        _state.value = state.value.copy(
                            alarmClockTracker = newAlarmData
                        )
                        changeAlarmEnabledUseCase(event.alarmClockTracker.id, newAlarmData.enabled)
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }

            SleepTrackerEvent.Refresh -> {
                _state.value = state.value.copy(isRefreshing = true)
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        getSleepData()
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                }
                _state.value = state.value.copy(isRefreshing = false)
            }
        }
    }
}