package com.example.fitnessapp.feature_app.presentation.AddAlarm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.AddAlarmUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalTime

class AddAlarmViewModel(
    private val addAlarmUseCase: AddAlarmUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddAlarmState())
    val state: State<AddAlarmState> = _state

    fun onEvent(event: AddAlarmEvent) {
        when (event) {
            AddAlarmEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }

            is AddAlarmEvent.ChangeVibrationState -> {
                _state.value = state.value.copy(
                    vibrationState = event.value
                )
            }

            AddAlarmEvent.AddAlarmClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(showIndicator = true)
                    val sleepTime = LocalTime.of(
                        (_state.value.sleepTimeStart[0].toString()+_state.value.sleepTimeStart[1].toString()).toInt(),
                        (_state.value.sleepTimeStart[3].toString()+_state.value.sleepTimeStart[4].toString()).toInt()
                    )
                    val wakeUpTime = LocalTime.of(
                        (_state.value.sleepTimeEnd[0].toString() + _state.value.sleepTimeEnd[1].toString()).toInt(),
                        (_state.value.sleepTimeEnd[3].toString() + _state.value.sleepTimeEnd[4].toString()).toInt()
                    )
                    try {
                        addAlarmUseCase(
                            SleepTracker(
                                0, "", "$sleepTime", false, ""
                            ),
                            AlarmClockTracker(
                                0, "", "", _state.value.vibrationState, "$wakeUpTime"
                            )
                        )
                        _state.value = state.value.copy(
                            isAdded = true
                        )
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            exception = e.message.toString(),
                            showIndicator = false
                        )
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }

            is AddAlarmEvent.SetSleepTime -> {
                _state.value = state.value.copy(sleepTimeStart = event.value)
            }
            is AddAlarmEvent.SetAlarmTime -> {
                _state.value = state.value.copy(sleepTimeEnd = event.value)
            }
        }
    }
}