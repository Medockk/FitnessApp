package com.example.fitnessapp.feature_app.presentation.StartWorkout

import android.util.Log
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

            is StartWorkoutEvent.ChangeUserRepeatable -> {
                _state.value = state.value.copy(userRepeatable = event.value.toInt())
                Log.e("event", _state.value.userRepeatable.toString())
            }
        }
    }
}