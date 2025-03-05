package com.example.fitnessapp.feature_app.presentation.StartWorkout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetWorkoutDetailsUseCase
import com.example.fitnessapp.feature_app.presentation.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartWorkoutViewModel(
    private val getWorkoutDetailsUseCase: GetWorkoutDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(StartWorkoutState())
    val state: State<StartWorkoutState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            getWorkoutDetails()
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getWorkoutDetails() {
        val details = getWorkoutDetailsUseCase(Route.StartWorkoutScreen.workout.workoutSprintID)

        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                workoutDescription = Route.StartWorkoutScreen.workout.description,
            )
        }
        if (details.isNotEmpty()) {
            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(videoUrl = details[0].videoUrl)
            }
        }
    }

    fun onEvent(event: StartWorkoutEvent) {
        when (event) {
            is StartWorkoutEvent.SetException -> {
                _state.value = state.value.copy(exception = event.exception)
            }

            is StartWorkoutEvent.ChangeUserRepeatable -> {
                _state.value = state.value.copy(userRepeatable = event.value.toInt())
            }

            StartWorkoutEvent.StartVideo -> {

            }

            StartWorkoutEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }

            is StartWorkoutEvent.SetExoplayer -> {
                _state.value = state.value.copy(exoPlayer = event.exoPlayer)
            }

            is StartWorkoutEvent.ChangeFullScreenOrientation -> {
                event.activity.requestedOrientation = event.orientation
            }
        }
    }
}