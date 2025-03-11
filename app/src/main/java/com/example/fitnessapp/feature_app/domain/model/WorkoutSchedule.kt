package com.example.fitnessapp.feature_app.domain.model

interface WorkoutSchedule {
    val id: Int
    val time: String
    val title: String
    val userID: String
    val date: String
}
