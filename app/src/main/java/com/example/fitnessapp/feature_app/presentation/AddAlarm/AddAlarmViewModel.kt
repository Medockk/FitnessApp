package com.example.fitnessapp.feature_app.presentation.AddAlarm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import com.example.fitnessapp.feature_app.domain.usecase.Sleep.AddAlarmUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddAlarmViewModel(
    private val addAlarmUseCase: AddAlarmUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddAlarmState())
    val state: State<AddAlarmState> = _state

    fun onEvent(event: AddAlarmEvent){
        when (event){
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
                    try {
                        addAlarmUseCase(
                            SleepTracker(
                                0,"","","",_state.value.sleepTimeStart,false,""
                            )
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
        }
    }
}