package com.example.fitnessapp.feature_app.presentation.RegisterPage

sealed class RegisterEvent {

    data class SelectPurpose(val value: Int) : RegisterEvent()
}