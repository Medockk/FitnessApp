package com.example.fitnessapp.feature_app.presentation.AddAlarm

sealed class AddAlarmEvent{

    data object ResetException : AddAlarmEvent()
    data class ChangeVibrationState(val value: Boolean) : AddAlarmEvent()
    data class SetSleepTime(val value: String) : AddAlarmEvent()
    data class SetAlarmTime(val value: String) : AddAlarmEvent()

    data object AddAlarmClick : AddAlarmEvent()
}
