package com.example.fitnessapp.feature_app.presentation.OnBoard

sealed class OnBoardEvent {

    data class NextPage(val value: Int) : OnBoardEvent()
}