package com.example.fitnessapp.feature_app.presentation.WorkoutTracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.feature_app.domain.usecase.User.GetHeartRateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.ChangeUserWorkoutStateUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetAllWorkoutUseCase
import com.example.fitnessapp.feature_app.domain.usecase.Workout.GetUserWorkoutUseCase
import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val getUserWorkoutUseCase: GetUserWorkoutUseCase,
    private val changeUserWorkoutStateUseCase: ChangeUserWorkoutStateUseCase,
    private val getAllWorkoutUseCase: GetAllWorkoutUseCase,
    private val getHeartRateUseCase: GetHeartRateUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WorkoutTrackerState())
    val state: State<WorkoutTrackerState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(showIndicator = true)
            try {
                getWorkoutBar()
                getUserWorkout()
                getAllWorkout()
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    exception = e.message.toString(),
                    showIndicator = false
                )
            }
            _state.value = state.value.copy(showIndicator = false)
        }
    }

    private suspend fun getWorkoutBar() {

        getHeartRateUseCase().collect {
            when (it) {
                is NetworkResult.Error<*> -> {
                    _state.value = state.value.copy(
                        exception = it.message ?: "Unknown error",
                        showIndicator = false
                    )
                }

                is NetworkResult.Loading<*> -> {
                    _state.value = state.value.copy(showIndicator = true)
                }

                is NetworkResult.Success<*> -> {
                    _state.value = state.value.copy(showIndicator = false)
                    it.data?.heartRateList?.forEach { char ->
                        if (_state.value.workoutBar.size < 7) {
                            if (char.isDigit()) {
                                withContext(Dispatchers.Main) {
                                    _state.value = state.value.copy(
                                        workoutBar = _state.value.workoutBar.plus(
                                            char.toString().toFloat()
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun getAllWorkout() {

        val allWorkout = getAllWorkoutUseCase()

        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                workoutList = allWorkout
            )
        }
    }

    private suspend fun getUserWorkout() {

        val userWorkout = getUserWorkoutUseCase()

        withContext(Dispatchers.Main) {
            _state.value = state.value.copy(
                userWorkoutList = userWorkout
            )
        }
    }

    fun onEvent(event: WorkoutEvent) {
        when (event) {
            WorkoutEvent.ResetException -> {
                _state.value = state.value.copy(
                    exception = ""
                )
            }

            is WorkoutEvent.ChangeUserWorkoutState -> {

                val newData = event.userWorkoutData.copy(
                    isTurnOn = !event.userWorkoutData.isTurnOn
                )
                val index = _state.value.userWorkoutList.indexOf(event.userWorkoutData)
                _state.value.userWorkoutList.apply {
                    val newList = this.toMutableList()
                    newList[index] = newData
                    _state.value = state.value.copy(
                        userWorkoutList = newList
                    )
                }
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        changeUserWorkoutStateUseCase(
                            userWorkoutData = newData
                        )
                    } catch (e: Exception) {
                        _state.value = state.value.copy(exception = e.message.toString())
                    }
                }
            }
        }
    }
}