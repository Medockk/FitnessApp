package com.example.fitnessapp.feature_app.presentation.StartWorkout

import androidx.media3.exoplayer.ExoPlayer

data class StartWorkoutState(
    val exception: String = "",

    val workoutDescription: String = "",
    val videoUrl: String = "",
    val exoPlayer: ExoPlayer? = null,

    val showIndicator: Boolean = false,
    val isVideoPlaying: Boolean = false,
    val isFullScreen: Boolean = false,
    val userRepeatable: Int = 1,
)