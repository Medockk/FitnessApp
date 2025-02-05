package com.example.fitnessapp.feature_app.presentation.AddAlarm

sealed class AddAlarmEvent{

    data object ResetException : AddAlarmEvent()
    data class ChangeVibrationState(val value: Boolean) : AddAlarmEvent()

    data object AddAlarmClick : AddAlarmEvent()
}
