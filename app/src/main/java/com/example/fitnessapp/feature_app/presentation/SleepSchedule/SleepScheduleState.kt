package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker
import java.time.LocalDate

data class SleepScheduleState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val sleepTime: String = "",
    val sleepPercent: Float = 0f,

    val sleepData: List<SleepTracker> = emptyList(),
    val alarmClockTracker: List<AlarmClockTracker> = emptyList(),
    val currentDay: Int = LocalDate.now().dayOfMonth
)
