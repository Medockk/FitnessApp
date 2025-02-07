package com.example.fitnessapp.feature_app.presentation.ProgressPhoto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.Gallery.GetAllGalleryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgressPhotoViewModel(
    private val getAllGalleryUseCase: GetAllGalleryUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProgressPhotoState())
    val state: State<ProgressPhotoState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getUserGallery()
            } catch (e: Exception) {
                _state.value = state.value.copy(exception = e.message.toString())
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getUserGallery() {

        val gallery = getAllGalleryUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(gallery = gallery)
        }
    }

    fun onEvent(event: ProgressPhotoEvent){
        when (event){
            ProgressPhotoEvent.ResetException -> {
                _state.value = state.value.copy(exception = "")
            }
        }
    }
}