package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeAlarmEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.ChangeSleepEnabledUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetAlarmClockDataByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetAlarmClockDataUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.GetSleepDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepScheduleViewModel(
    private val getSleepDataUseCase: GetSleepDataUseCase,
    private val getAlarmClockDataUseCase: GetAlarmClockDataUseCase,
    private val changeSleepEnabledUseCase: ChangeSleepEnabledUseCase,
    private val changeAlarmEnabledUseCase: ChangeAlarmEnabledUseCase,
    private val getSleepDataByDateUseCase: GetSleepDataByDateUseCase,
    private val getAlarmClockDataByDateUseCase: GetAlarmClockDataByDateUseCase
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
        val alarm = getAlarmClockDataUseCase()

        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                sleepData = sleep,
                alarmClockTracker = alarm,
                sleepTime = if (sleep.isNotEmpty()) sleep.first().lastSleep else "Ничего нет :("
            )
        }
    }

    fun onEvent(event: SleepScheduleEvent) {
        when (event) {
            SleepScheduleEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }

            is SleepScheduleEvent.ChangeSleepEnabled -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val index = _state.value.sleepData.indexOf(event.sleepTracker)
                    val sleep = event.sleepTracker.copy(enabled = !event.sleepTracker.enabled)
                    _state.value = state.value.copy(
                        showIndicator = true,
                        sleepData = _state.value.sleepData - _state.value.sleepData[index] + sleep
                    )
                    try {
                        changeSleepEnabledUseCase(event.sleepTracker.copy(enabled = !event.sleepTracker.enabled))
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString(),
                            showIndicator = false
                        )
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }

            is SleepScheduleEvent.ChangeAlarmEnabled -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val index = _state.value.alarmClockTracker.indexOf(event.alarmClockTracker)
                    val alarm =
                        event.alarmClockTracker.copy(enabled = !event.alarmClockTracker.enabled)
                    _state.value = state.value.copy(
                        showIndicator = true,
                        alarmClockTracker = _state.value.alarmClockTracker - _state.value.alarmClockTracker[index] + alarm
                    )
                    try {
                        changeAlarmEnabledUseCase(event.alarmClockTracker.copy(enabled = !event.alarmClockTracker.enabled))
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString(),
                            showIndicator = false
                        )
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }

            is SleepScheduleEvent.MonthClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(showIndicator = true)
                    try {
                        _state.value = state.value.copy(
                            sleepData = getSleepDataByDateUseCase(event.value.year, event.value.month.value, event.value.dayOfMonth),
                            alarmClockTracker = getAlarmClockDataByDateUseCase(event.value.year, event.value.month.value, event.value.dayOfMonth),
                            currentDay = event.value.dayOfMonth
                        )
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }
        }
    }
}