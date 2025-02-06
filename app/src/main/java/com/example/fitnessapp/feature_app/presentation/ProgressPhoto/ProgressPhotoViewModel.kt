package com.example.fitnessapp.feature_app.presentation.ProgressPhoto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProgressPhotoViewModel : ViewModel() {

    private val _state = mutableStateOf(ProgressPhotoState())
    val state: State<ProgressPhotoState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            getUserGallery()
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getUserGallery() {

    }

    fun onEvent(event: ProgressPhotoEvent){
        when (event){
            ProgressPhotoEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }
        }
    }
}