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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SleepScheduleViewModel @Inject constructor(
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
                    val newSleepData = event.sleepTracker.copy(enabled = !event.sleepTracker.enabled)
                    _state.value = state.value.copy(
                        sleepData = _state.value.sleepData - event.sleepTracker + newSleepData
                    )
                    try {
                        changeSleepEnabledUseCase(event.sleepTracker.id, newSleepData.enabled)
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString()
                        )
                    }
                }
            }

            is SleepScheduleEvent.ChangeAlarmEnabled -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val newAlarmData = event.alarmClockTracker.copy(enabled = !event.alarmClockTracker.enabled)
                    _state.value = state.value.copy(
                        alarmClockTracker = _state.value.alarmClockTracker - event.alarmClockTracker + newAlarmData
                    )
                    try {
                        changeAlarmEnabledUseCase(event.alarmClockTracker.id, newAlarmData.enabled)
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString(),
                        )
                    }
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