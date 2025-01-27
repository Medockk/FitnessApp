package com.example.fitnessapp.feature_app.presentation.Profile

sealed class ProfileEvent {

    data object ResetException : ProfileEvent()
    data class ChangeNotificationState(val value: Boolean) : ProfileEvent()
}