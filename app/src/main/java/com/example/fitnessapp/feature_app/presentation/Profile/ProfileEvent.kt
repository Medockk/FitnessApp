package com.example.fitnessapp.feature_app.presentation.Profile

import android.net.Uri
import com.example.fitnessapp.feature_app.domain.model.UserDataEntity

sealed class ProfileEvent {

    data object ResetException : ProfileEvent()
    data class ChangeNotificationState(val value: Boolean) : ProfileEvent()

    data class SetUserDataDao(val value: UserDataEntity) : ProfileEvent()

    data class ChangeImageView(val uri: Uri) : ProfileEvent()
    data class ChangeImage(val byteArray: ByteArray?) : ProfileEvent()
}