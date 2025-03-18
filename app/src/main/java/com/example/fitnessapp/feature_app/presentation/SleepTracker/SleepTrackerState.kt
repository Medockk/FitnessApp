package com.example.fitnessapp.feature_app.presentation.SleepTracker

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel
import kotlinx.datetime.LocalDateTime

data class SleepTrackerState(
    val exception: String = "",

    val barList: List<Float> = emptyList(),
    val lastSleep: String = "",
    val currentTime: LocalDateTime = LocalDateTime(1,1,1,1,1),

    val sleepData: SleepTrackerModel? = null,
    val sleepEnd: String = "",
    val alarmClockTracker: AlarmClockTrackerModel? = null,
    val alarmEnd: String = "",

    val showIndicator: Boolean = false,
)
