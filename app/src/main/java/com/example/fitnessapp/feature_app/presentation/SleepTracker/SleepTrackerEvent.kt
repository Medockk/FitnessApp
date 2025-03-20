package com.example.fitnessapp.feature_app.presentation.SleepTracker

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel

sealed class SleepTrackerEvent {

    data object ResetException : SleepTrackerEvent()
    data object Refresh : SleepTrackerEvent()

    data class ChangeSleepWorkout(val sleepTracker: SleepTrackerModel) : SleepTrackerEvent()
    data class ChangeAlarmState(val alarmClockTracker: AlarmClockTrackerModel) : SleepTrackerEvent()
}