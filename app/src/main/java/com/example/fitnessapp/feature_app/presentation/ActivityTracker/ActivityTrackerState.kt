package com.example.fitnessapp.feature_app.presentation.ActivityTracker

import com.example.fitnessapp.feature_app.domain.model.CurrentPurpose
import com.example.fitnessapp.feature_app.domain.model.LastActivityData

data class ActivityTrackerState(

    val lastActivity: List<LastActivityData> = emptyList(),

    val exception: String = "",

    val activityProgress: List<Int> = listOf(0, 45, 752, 45 ,5646,4654, 654),
    val currentPurpose: List<CurrentPurpose> = listOf(
        CurrentPurpose(
            0, "", "8Л", "Воды",
            "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//glass.png"
        ),
        CurrentPurpose(
            0, "", "2400", "Шагов",
            "https://qappxorzuldxgbbwlxvt.supabase.co/storage/v1/object/public/image//boots.png"
        ),
    )
)
