package com.example.fitnessapp.feature_app.presentation.StartWorkout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StartWorkoutViewModel : ViewModel() {

    private val _state = mutableStateOf(StartWorkoutState())
    val state: State<StartWorkoutState> = _state

    init {

    }

    fun onEvent(event: StartWorkoutEvent){
        when (event){
            StartWorkoutEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}