package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import com.example.fitnessapp.feature_app.domain.model.SleepTracker

sealed class SleepScheduleEvent {

    data object ResetException : SleepScheduleEvent()

    data class ChangeEnabled(val sleepTracker: SleepTracker) : SleepScheduleEvent()
}