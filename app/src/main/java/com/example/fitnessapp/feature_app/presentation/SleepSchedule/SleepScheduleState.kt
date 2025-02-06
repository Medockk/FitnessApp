package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker

data class SleepScheduleState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val sleepTime: String = "",
    val sleepPercent: Float = 0f,

    val sleepData: SleepTracker? = null,
    val alarmClockTracker: AlarmClockTracker? = null,
)
