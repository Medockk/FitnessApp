package com.example.fitnessapp.feature_app.presentation.AddAlarm

data class AddAlarmState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val sleepTimeStart: String = "21:00",
    val sleepTimeValue: String = "8:30",
    val repeatDays: String = "Never",
    val vibrationState: Boolean = false,
)
