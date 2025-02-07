package com.example.fitnessapp.feature_app.presentation.CompareResult

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CompareResultViewModel : ViewModel() {

    private val _state = mutableStateOf(CompareResultState())
    val state: State<CompareResultState> = _state


    fun onEvent(event: CompareResultEvent){
        when (event){
            CompareResultEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }
        }
    }
}