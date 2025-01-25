package com.example.fitnessapp.feature_app.presentation.CreateProfile

data class CreateProfileState(
    val gender: String = "",
    val birthdayData: String = "",
    val weight: String = "",
    val height: String = "",

    val exception: String = "",
)
