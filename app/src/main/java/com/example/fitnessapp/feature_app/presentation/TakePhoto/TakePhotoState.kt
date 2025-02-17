package com.example.fitnessapp.feature_app.presentation.TakePhoto

import android.graphics.Bitmap

data class TakePhotoState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val imageBitmaps: List<Bitmap> = emptyList(),
)
