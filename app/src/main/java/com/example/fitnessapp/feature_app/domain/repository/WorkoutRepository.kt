package com.example.fitnessapp.feature_app.domain.repository

import com.example.fitnessapp.feature_app.domain.utils.NetworkResult
import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.model.WorkoutDetails
import com.example.fitnessapp.feature_app.domain.model.WorkoutSchedule
import com.example.fitnessapp.feature_app.domain.model.WorkoutSprint
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    suspend fun getUserWorkout() : List<UserWorkoutData>
    suspend fun changeUserWorkoutState(userWorkoutData: UserWorkoutData)
    suspend fun addLastActivity(title: String, image: String)

    suspend fun getAllWorkout() : List<WorkoutData>
    suspend fun getWorkoutSprint(sprintNumber: Int) : List<WorkoutSprint>
    suspend fun getWorkoutDetails(workoutSprintID: Int) : List<WorkoutDetails>

    suspend fun getWorkoutSchedule() : Flow<NetworkResult<List<WorkoutSchedule>>>
    suspend fun getWorkoutScheduleByDate(year: Int, month: Int, day: Int) : List<WorkoutSchedule>
    suspend fun setWorkoutSchedule(title: String, hour: Int, minute: Int)
}