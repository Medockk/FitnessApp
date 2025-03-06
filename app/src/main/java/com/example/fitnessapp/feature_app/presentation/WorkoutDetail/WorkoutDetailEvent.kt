package com.example.fitnessapp.feature_app.presentation.WorkoutDetail

import com.example.fitnessapp.feature_app.domain.model.WorkoutData

sealed class WorkoutDetailEvent{

    data class AddLastActivity(val value: WorkoutData) : WorkoutDetailEvent()
    data object ResetException : WorkoutDetailEvent()
    data object ResetIsCompleteState : WorkoutDetailEvent()
}
