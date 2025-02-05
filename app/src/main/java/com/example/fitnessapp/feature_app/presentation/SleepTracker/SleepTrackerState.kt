package com.example.fitnessapp.feature_app.presentation.SleepTracker

import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import kotlinx.datetime.LocalDateTime

data class SleepTrackerState(
    val exception: String = "",

    val barList: List<Float> = emptyList(),
    val lastSleep: String = "",
    val currentTime: LocalDateTime = LocalDateTime(1,1,1,1,1),

    val sleepData: List<SleepTracker> = emptyList(),

    val showIndicator: Boolean = false,
)
