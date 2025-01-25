package com.example.fitnessapp.feature_app.presentation.CreateProfile

sealed class CreateProfileEvent {

    data object ResetException : CreateProfileEvent()

    data class EnterGender(val value: String) : CreateProfileEvent()
    data class EnterBirthdayData(val value: String) : CreateProfileEvent()
    data class EnterWeight(val value: String) : CreateProfileEvent()
    data class EnterHeight(val value: String) : CreateProfileEvent()

    data object CreateProfileClick : CreateProfileEvent()
    data object ChangeDropDownMenuState : CreateProfileEvent()
}