package com.example.fitnessapp.feature_app.presentation.Comparison

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ComparisonViewModel : ViewModel() {

    private val _state = mutableStateOf(ComparisonState())
    val state: State<ComparisonState> = _state

    init {

    }

    fun onEvent(event: ComparisonEvent){
        when (event){
            ComparisonEvent.ComparisonClick -> {

            }
            ComparisonEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }
        }
    }
}