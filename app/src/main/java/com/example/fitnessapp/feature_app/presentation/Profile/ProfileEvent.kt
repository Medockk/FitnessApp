package com.example.fitnessapp.feature_app.presentation.Profile

import android.net.Uri

sealed class ProfileEvent {

    data object ResetException : ProfileEvent()
    data class ChangeNotificationState(val value: Boolean) : ProfileEvent()

    data class ChangeImageView(val uri: Uri) : ProfileEvent()
    data class ChangeImage(val byteArray: ByteArray?) : ProfileEvent()
}