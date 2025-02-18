package com.example.fitnessapp.feature_app.presentation.AddAlarm

data class AddAlarmState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val isAdded: Boolean = false,

    val sleepTimeStart: String = "21:00",
    val sleepTimeEnd: String = "8:30",
    val repeatDays: String = "Never",
    val vibrationState: Boolean = false,

    val commonSleepTimeStartList: List<String> = listOf(
        "20:00", "21:00", "22:00", "23:00"
    ),
    val commonSleepTimeEndList: List<String> = listOf(
        "06:00", "06:30", "07:00", "07:30"
    ),
)
