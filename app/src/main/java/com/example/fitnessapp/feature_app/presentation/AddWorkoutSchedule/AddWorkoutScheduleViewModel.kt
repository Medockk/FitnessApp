package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.extensions.isNotNull
import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.usecase.Workout.SetWorkoutScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime

class AddWorkoutScheduleViewModel(
    private val setWorkoutScheduleUseCase: SetWorkoutScheduleUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddWorkoutScheduleState())
    val state: State<AddWorkoutScheduleState> = _state

    init {
        val currentTime = Clock.System.now().toString().dropLast(1)
        val localDataTime = LocalDateTime.parse(currentTime)
        _state.value = state.value.copy(
            hour = localDataTime.hour.toString(),
            minute = localDataTime.minute.toString(),
            year = localDataTime.year.toString(),
            currentDayName = localDataTime.dayOfWeek.name[0].toString() +
                    localDataTime.dayOfWeek.name[1].toString() +
                    localDataTime.dayOfWeek.name[2].toString(),
            monthName = localDataTime.month.name,
            currentData = localDataTime.date.dayOfMonth.toString()
        )
    }

    fun onEvent(event: AddWorkoutScheduleEvent) {
        when (event) {
            AddWorkoutScheduleEvent.AddWorkout -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(showIndicator = true)
                    if (_state.value.hour.toIntOrNull() != null && _state.value.minute.toIntOrNull() != null){
                        try {
                            setWorkoutScheduleUseCase(
                                _state.value.title, _state.value.hour.toInt(), _state.value.minute.toInt()
                            )
                        } catch (e: Exception) {
                            _state.value = state.value.copy(exception = e.message.toString())
                        }
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }

            AddWorkoutScheduleEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            is AddWorkoutScheduleEvent.SetWorkoutTitle -> {
                _state.value = state.value.copy(title = event.value)
            }

            is AddWorkoutScheduleEvent.HourChange -> {
                _state.value = state.value.copy(hour = event.value.toString())
            }
            is AddWorkoutScheduleEvent.MinuteChange -> {
                _state.value = state.value.copy(minute = event.value.toString())
            }
        }
    }
}