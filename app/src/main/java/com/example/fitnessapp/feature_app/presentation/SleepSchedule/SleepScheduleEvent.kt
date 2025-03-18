package com.example.fitnessapp.feature_app.presentation.SleepSchedule

import com.example.fitnessapp.feature_app.domain.model.AlarmClockTrackerModel
import com.example.fitnessapp.feature_app.domain.model.SleepTrackerModel
import java.time.LocalDate

sealed class SleepScheduleEvent {

    data object ResetException : SleepScheduleEvent()

    data class MonthClick(val value: LocalDate) : SleepScheduleEvent()
    data class ChangeSleepEnabled(val sleepTracker: SleepTrackerModel) : SleepScheduleEvent()
    data class ChangeAlarmEnabled(val alarmClockTracker: AlarmClockTrackerModel) : SleepScheduleEvent()
}