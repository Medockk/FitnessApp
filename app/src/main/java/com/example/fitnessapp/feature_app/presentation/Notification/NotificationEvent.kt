package com.example.fitnessapp.feature_app.presentation.Notification

sealed class NotificationEvent {

    data object ResetException : NotificationEvent()
}