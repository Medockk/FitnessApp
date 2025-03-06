package com.example.fitnessapp.feature_app.presentation.WorkoutSchedule

import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import java.time.LocalDate

data class WorkoutScheduleState(
    val exception: String = "",

    val workoutSchedule: List<WorkoutSchedule> = emptyList(),

    val showIndicator: Boolean = false,
    val currentDay: Int = if (LocalDate.now().isLeapYear && LocalDate.now().year %4 == 0){
        LocalDate.now().dayOfMonth -1
    }else{
        LocalDate.now().dayOfMonth
    }
)
