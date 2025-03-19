package com.example.fitnessapp.feature_app.presentation.OnBoard

data class OnBoardState(
    var onBoardItem: List<OnBoardItem> = onBoardItemList,

    val currentPage: Int = 0,
    val isComplete: Boolean = false,
)
