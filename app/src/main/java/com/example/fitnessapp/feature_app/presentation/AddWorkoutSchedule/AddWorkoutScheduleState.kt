package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule

data class AddWorkoutScheduleState(
    val exception: String = "",

    val currentData: String = "",
    val monthName: String = "",
    val hour: String = "1",
    val minute: String = "1",
    val year: String = "",
    val currentDayName: String = "",

    val title: String = "Вверхняя часть",
    val showIndicator: Boolean = false,
)
