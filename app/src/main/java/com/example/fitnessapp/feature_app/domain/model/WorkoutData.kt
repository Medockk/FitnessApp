package com.example.fitnessapp.feature_app.domain.model

interface UserWorkoutData {
    val id: Int
    val userID: String
    val image: String
    val title: String
    val time: String
    var isTurnOn: Boolean
}

interface WorkoutData {
    val id: Int
    val image: String
    val title: String
    val workoutCount: String
    val itemCount: String
}
