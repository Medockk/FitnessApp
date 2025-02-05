package com.example.fitnessapp.feature_app.presentation.WorkoutDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutSprintUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkoutDetailViewModel(
    private val getWorkoutSprintUseCase: GetWorkoutSprintUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WorkoutDetailState())
    val state: State<WorkoutDetailState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getWorkoutSprint()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getWorkoutSprint() {

        val sprint1 = getWorkoutSprintUseCase(1)
        val sprint2 = getWorkoutSprintUseCase(2)

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                sprint1 = sprint1,
                sprint2 = sprint2
            )
        }
    }
}