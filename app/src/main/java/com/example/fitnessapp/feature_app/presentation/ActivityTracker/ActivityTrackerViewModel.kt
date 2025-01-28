package com.example.fitnessapp.feature_app.presentation.ActivityTracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            try {
                getLastActivity()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString()
                )
            }
        }
    }

    private suspend fun getLastActivity() {

        val activity = getLastActivityUseCase()

        withContext(Dispatchers.Main){
            _state.value = state.value.copy(
                lastActivity = activity
            )
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