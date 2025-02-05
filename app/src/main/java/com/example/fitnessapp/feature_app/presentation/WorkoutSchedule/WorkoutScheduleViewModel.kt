package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutScheduleViewModel(
    private val getWorkoutScheduleUseCase: GetWorkoutScheduleUseCase
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

        val schedule = getWorkoutScheduleUseCase()

        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                workoutSchedule = schedule
            )
        }
    }

    fun onEvent(event: WorkoutScheduleEvent) {
        when (event) {
            WorkoutScheduleEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}