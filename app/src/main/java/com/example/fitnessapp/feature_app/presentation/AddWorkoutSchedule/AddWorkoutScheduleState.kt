package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule

data class AddWorkoutScheduleState(
    val exception: String = "",

    val currentData: String = "",
    val monthName: String = "",
    val hour: String = "",
    val minute: String = "",
    val year: String = "",
    val currentDayName: String = "",

    val title: String = "",
    val showIndicator: Boolean = false,
)
