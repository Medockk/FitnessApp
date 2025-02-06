package com.example.fitnessapp.feature_app.presentation.SleepTracker

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTracker
import com.example.fitnessapp.feature_app.domain.model.SleepTracker

sealed class SleepTrackerEvent {

    data object ResetException : SleepTrackerEvent()

    data class ChangeSleepWorkout(val sleepTracker: SleepTracker) : SleepTrackerEvent()
    data class ChangeAlarmState(val alarmClockTracker: AlarmClockTracker) : SleepTrackerEvent()
}