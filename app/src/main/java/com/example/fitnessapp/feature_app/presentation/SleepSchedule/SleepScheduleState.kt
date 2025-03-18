package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel
import java.time.LocalDate

data class SleepScheduleState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val sleepTime: String = "",
    val sleepPercent: Float = 0f,

    val sleepData: List<SleepTrackerModel> = emptyList(),
    val alarmClockTracker: List<AlarmClockTrackerModel> = emptyList(),
    val currentDay: Int = LocalDate.now().dayOfMonth
)
