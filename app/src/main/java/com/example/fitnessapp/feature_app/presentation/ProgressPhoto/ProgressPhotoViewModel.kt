package com.example.fitnessapp.feature_app.presentation.ProgressPhoto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.usecase.Compare.GetAllGalleryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProgressPhotoViewModel @Inject constructor(
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

        getAllGalleryUseCase().collect {
            when(it){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(exception = it.message ?: "unknown error", showIndicator = false)
                }
                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }
                is NetworkResult.Success<*> -> {
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(gallery = it.data ?: emptyList(), showIndicator = false)
                    }
                }
            }
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