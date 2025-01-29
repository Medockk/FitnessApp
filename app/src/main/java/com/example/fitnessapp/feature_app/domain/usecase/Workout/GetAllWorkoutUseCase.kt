package com.example.fitnessapp.feature_app.domain.usecase.Workout

import com.example.fitnessapp.feature_app.domain.model.WorkoutData
import com.example.fitnessapp.feature_app.domain.repository.WorkoutRepository

class GetAllWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke() : List<WorkoutData>{
        return workoutRepository.getAllWorkout()
    }
}