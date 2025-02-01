package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint

interface WorkoutRepository {

    suspend fun getUserWorkout() : List<UserWorkoutData>
    suspend fun changeUserWorkoutState(userWorkoutData: UserWorkoutData)

    suspend fun getAllWorkout() : List<WorkoutData>
    suspend fun getWorkoutSprint(sprintNumber: Int) : List<WorkoutSprint>

    suspend fun getWorkoutSchedule() : List<WorkoutSchedule>
    suspend fun setWorkoutSchedule(workoutSchedule: WorkoutSchedule)
}