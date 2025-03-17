package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutScheduleByDateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorkoutScheduleViewModel @Inject constructor(
    private val getWorkoutScheduleUseCase: GetWorkoutScheduleUseCase,
    private val getWorkoutScheduleByDateUseCase: GetWorkoutScheduleByDateUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WorkoutScheduleState())
    val state: State<WorkoutScheduleState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getSchedule()
            } catch (e: Exception) {
                _state.value =
                    state.value.copy(exception = e.message.toString(), showIndicator = false)
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getSchedule() {

        getWorkoutScheduleUseCase().collect {
            when (it){
                is NetworkResult.Error<*> -> {_state.value = state.value.copy(exception = it.message ?: "unknown error", showIndicator = false)}
                is NetworkResult.Loading<*> -> {_state.value =state.value.copy(showIndicator = true)}
                is NetworkResult.Success<*> -> {
                    withContext(Dispatchers.Main) {
                        _state.value = state.value.copy(
                            workoutSchedule = it.data ?: emptyList(),
                            showIndicator = false
                        )
                    }
                }
            }
        }

    }

    fun onEvent(event: WorkoutScheduleEvent) {
        when (event) {
            WorkoutScheduleEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            is WorkoutScheduleEvent.MonthClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.value = state.value.copy(showIndicator = true)
                    val date = event.value
                    val workoutSchedule = getWorkoutScheduleByDateUseCase(
                        date.year,
                        date.month.value,
                        date.dayOfMonth
                    )
                    try {
                        withContext(Dispatchers.Main) {
                            _state.value = state.value.copy(
                                currentDay = date.dayOfMonth,
                                workoutSchedule = workoutSchedule
                            )
                        }
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                    _state.value = state.value.copy(showIndicator = false)
                }
            }
        }
    }
}