package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData

interface WorkoutRepository {

    suspend fun getUserWorkout() : List<UserWorkoutData>
    suspend fun changeUserWorkoutState(userWorkoutData: UserWorkoutData)

    suspend fun getAllWorkout() : List<WorkoutData>
}