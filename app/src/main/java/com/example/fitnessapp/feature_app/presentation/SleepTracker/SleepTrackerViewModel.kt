package com.example.fitnessapp.feature_app.presentation.SleepTracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeAlarmEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeSleepEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetAlarmClockDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime

class SleepTrackerViewModel(
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

        val barList = getHeartRateUseCase()
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
                        _state.value = state.value.copy(
                            sleepData = _state.value.sleepData?.copy(enabled = !event.sleepTracker.enabled)
                        )
                        changeSleepEnabledUseCase(event.sleepTracker.copy(enabled = !event.sleepTracker.enabled))
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
                        _state.value = state.value.copy(
                            alarmClockTracker = _state.value.alarmClockTracker?.copy(enabled = !event.alarmClockTracker.enabled)
                        )
                        changeAlarmEnabledUseCase(event.alarmClockTracker.copy(enabled = !event.alarmClockTracker.enabled))
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }
        }
    }
}