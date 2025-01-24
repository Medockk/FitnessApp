package com.example.fitnessapp.feature_app.presentation.OnBoard

import com.example.fitnessapp.R

data class OnBoardState(
    var onBoardItem: OnBoardItem = OnBoardItem(R.drawable.onboard_screen1, "",""),
    val pagesCount: Int = 3,

    val currentPage: Int = 0,
    val isComplete: Boolean = false,
)
