package com.example.fitnessapp.feature_app.presentation.WorkoutTracker

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData

sealed class WorkoutEvent {

    data class ChangeUserWorkoutState(val userWorkoutData: UserWorkoutData) : WorkoutEvent()

    data object ResetException : WorkoutEvent()
}