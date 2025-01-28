package com.example.fitnessapp.feature_app.presentation.Congratulations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CongratulationViewModel : ViewModel() {

    private val _state = mutableStateOf(CongratulationState())
    val state: State<CongratulationState> = _state

}