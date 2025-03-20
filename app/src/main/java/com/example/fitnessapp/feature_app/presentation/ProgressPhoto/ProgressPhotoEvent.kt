package com.example.fitnessapp.feature_app.presentation.ProgressPhoto

sealed class ProgressPhotoEvent {

    data object ResetException : ProgressPhotoEvent()
    data object Refresh : ProgressPhotoEvent()
}