package com.example.fitnessapp.feature_app.presentation.ActivityTracker

sealed class ActivityTrackerEvent {

    data object ResetException: ActivityTrackerEvent()

    data object AddPurpose : ActivityTrackerEvent()
}