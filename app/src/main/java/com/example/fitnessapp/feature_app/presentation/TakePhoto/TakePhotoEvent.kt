package com.example.fitnessapp.feature_app.presentation.TakePhoto

import android.graphics.Bitmap

sealed class TakePhotoEvent {

    data object ResetException : TakePhotoEvent()
    data class TakePhoto(val bitmap: Bitmap) : TakePhotoEvent()
}