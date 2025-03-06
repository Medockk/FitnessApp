package com.example.fitnessapp.feature_app.presentation.StartWorkout

import android.app.Activity
import androidx.media3.exoplayer.ExoPlayer

sealed class StartWorkoutEvent {

    data object ResetException: StartWorkoutEvent()
    data class SetException(val exception: String) : StartWorkoutEvent()
    data class SetExoplayer(val exoPlayer: ExoPlayer) : StartWorkoutEvent()
    data class ChangeFullScreenOrientation(val activity: Activity, val orientation: Int) : StartWorkoutEvent()

    data class ChangeUserRepeatable(val value: String) : StartWorkoutEvent()
    data object ChangeVideoPosition : StartWorkoutEvent()
}