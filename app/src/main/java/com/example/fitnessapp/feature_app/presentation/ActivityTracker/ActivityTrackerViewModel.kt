package com.example.fitnessapp.feature_app.presentation.ActivityTracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.NetworkResult
import com.example.fitnessapp.feature_app.domain.usecase.User.GetLastActivityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityTrackerViewModel(
    private val getLastActivityUseCase: GetLastActivityUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ActivityTrackerState())
    val state: State<ActivityTrackerState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getLastActivity()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getLastActivity() {

        getLastActivityUseCase().collect{activity ->
            when (activity){
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(exception = activity.message ?: "unknown error :(", showIndicator = false)
                }
                is NetworkResult.Loading<*> -> {_state.value = state.value.copy(showIndicator = true)}
                is NetworkResult.Success<*> -> {
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            lastActivity = activity.data ?: emptyList(),
                            showIndicator = false
                        )
                    }
                }
            }
        }

    }

    fun onEvent(event: ActivityTrackerEvent){
        when (event){
            ActivityTrackerEvent.AddPurpose -> {}
            ActivityTrackerEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }
        }
    }
}