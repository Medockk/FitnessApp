package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker

sealed class SleepScheduleEvent {

    data object ResetException : SleepScheduleEvent()

    data class ChangeSleepEnabled(val sleepTracker: SleepTracker) : SleepScheduleEvent()
    data class ChangeAlarmEnabled(val alarmClockTracker: AlarmClockTracker) : SleepScheduleEvent()
}