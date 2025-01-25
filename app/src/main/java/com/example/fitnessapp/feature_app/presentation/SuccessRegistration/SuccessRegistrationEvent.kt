package com.example.fitnessapp.feature_app.presentation.SuccessRegistration

sealed class SuccessRegistrationEvent {

    data object ResetException : SuccessRegistrationEvent()
}