package com.example.fitnessapp.feature_app.presentation.TakePhoto

import android.graphics.Bitmap

data class TakePhotoState(
    val exception: String = "",
    val showIndicator: Boolean = false,

    val imageByteArray: List<ByteArray> = emptyList(),
    val imageBitmap: List<Bitmap> = emptyList(),
)
