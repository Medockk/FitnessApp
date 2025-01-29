package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.UserWorkoutData
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class GetUserWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke() : List<UserWorkoutData>{
        return workoutRepository.getUserWorkout()
    }
}