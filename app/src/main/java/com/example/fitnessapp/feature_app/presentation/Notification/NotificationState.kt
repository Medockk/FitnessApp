package com.example.fitnessapp.feature_app.presentation.Notification

import com.example.fitnessapp.feature_app.domain.model.NotificationData

data class NotificationState(

    val notifications: List<NotificationData> = emptyList(),

    val exception: String = "",
    val showIndicator: Boolean = false,
)
